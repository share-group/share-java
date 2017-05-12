root=$(pwd)  
cd $root/gongzuoshi-server
rm -rf .gradle
rm -rf $root/gongzuoshi-server/.git/index.lock
rm -rf /root/.gradle/caches/modules-2/modules-2.lock

#开启gradle daemon模式
rm -rf ~/.gradle/gradle.properties
echo "org.gradle.daemon=false" > ~/.gradle/gradle.properties

#指定nexus私服作为仓库
rm -rf /root/.npmrc
echo "registry=http://192.168.1.110:8081/repository/gatherup/" > /root/.npmrc

git pull
git checkout develop
git pull
rm -rf /usr/bin/run-all
rm -rf $root/config/jvm 
rm -rf $root/shell
mkdir -p $root/shell


# 更新脚本
rm -rf $root/fab.sh && cp $root/gongzuoshi-server/fab.sh $root/fab.sh && chmod 777 $root/fab.sh
rm -rf $root/build.sh && cp $root/gongzuoshi-server/build.sh $root/build.sh && chmod 777 $root/build.sh
rm -rf $root/SmsUtil.sh && cp $root/gongzuoshi-server/SmsUtil.sh $root/SmsUtil.sh && chmod 777 $root/SmsUtil.sh
rm -rf $root/clearlog.sh && cp $root/gongzuoshi-server/clearlog.sh $root/clearlog.sh && chmod 777 $root/clearlog.sh
rm -rf $root/IsDeadSmsUtil.sh && cp $root/gongzuoshi-server/IsDeadSmsUtil.sh $root/IsDeadSmsUtil.sh && chmod 777 $root/IsDeadSmsUtil.sh
rm -rf $root/ssl.sh && cp $root/gongzuoshi-server/ssl.sh $root/ssl.sh && chmod 777 $root/ssl.sh

memory='-Xms128m -Xmx128m'

jvm='-Dfile.encoding=utf8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:ParallelGCThreads=4 -XX:CompileThreshold=4 -XX:+UseParallelGC -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:+UseFastAccessorMethods -XX:+DoEscapeAnalysis'

echo $jvm > $root/config/jvm

# 杀死redis(主要是方便外网测试服)
echo "#!/bin/bash
redislist=\$(ps -ef|grep redis|grep -v grep|awk '{print \$2}')
for redis in \$redislist
do
kill -15 \$redis
done
redis-server /usr/local/redis/redis.conf
" >> /usr/bin/run-all && chmod 777 /usr/bin/run-all

# 编译java
gradle clean deploy

cd $root/gongzuoshi-server 
for dir in *
do
	tmp=$(echo $dir | grep 'gu\-')
	if [ ! $tmp ]; then
		continue
	fi
	
	rm -rf $root/$tmp/lib
	rm -rf $root/$tmp/log/gc.log
	rm -rf $root/$tmp/resources

	startName=${tmp#*-}
	className=${startName:0:1}
	mainClass='com.gu.'$startName'.Start'$(echo $className | tr '[a-z]' '[A-Z]')${startName:1}
	rm -rf /usr/bin/run-$startName
	
	if [ $startName = "test" ]; then
		rm -rf $root/$tmp
		continue;
	fi

	if [ ! -d $tmp/compile ];then
		continue
	fi

	mkdir -p $root/$tmp/lib
	mkdir -p $root/$tmp/log

	echo 'building '$tmp' ...'
	rsync -av $root/gongzuoshi-server/$tmp/compile/ $root/$tmp/lib/
	if [ -d $root/gongzuoshi-server/gu-core/resources/ ]; then
		mkdir -p $root/$tmp/resources
		rsync -av $root/gongzuoshi-server/gu-core/resources/ $root/$tmp/resources/
	fi

	rm -rf $root/gongzuoshi-server/$tmp/compile/
	rm -rf $root/gongzuoshi-server/$tmp/build/
	rm -rf /usr/bin/run-$startName
	
	echo "#!/bin/bash
processlist=\$(ps -ef|grep $root/$tmp|grep -v grep|awk '{print \$2}')
for proc in \$processlist
do
kill -15 \$proc
done

classpath=\"$root/etc\"
for jar in \`ls $root/$tmp/lib/*.jar\`
do
classpath=\$classpath:\$jar
done
cd $root/$tmp
echo '$startName start success'
java -Xverify:none -server $memory $jvm -Xloggc:$root/$tmp/log/gc.log -Dproject=$tmp -classpath \$classpath $mainClass > /dev/null &
	" > /usr/bin/run-$startName && chmod 777 /usr/bin/run-$startName
	yes | cp -rf /usr/bin/run-$startName $root/shell/
	echo run-$startName >> /usr/bin/run-all && chmod 777 /usr/bin/run-all
done


rm -rf $root/.gradle

# 打包之前，把webpack的打包模式改成生产环境
sed -i "s/devtool: 'eval'/devtool: false/" $root/gongzuoshi-server/vue-web/webpack.config.js
sed -i "s/devtool: 'eval'/devtool: false/" $root/gongzuoshi-server/vue-mobile/webpack.config.js

# 文件哈希打包
sed -i "s/\[name\].js/\[name\].\[chunkHash:8\].js/" $root/gongzuoshi-server/vue-web/webpack.config.js
sed -i "s/\[name\].css/\[name\].\[chunkHash:8\].css/" $root/gongzuoshi-server/vue-web/webpack.config.js

# 修改爬虫地址
sed -i "s/http:\/\/127.0.0.1/https:\/\/test.gatherup.cc/" $root/gongzuoshi-server/node-crawler/config.js

# 打包vue-web文件
echo 'pack vue-web start ...'
rm -rf $root/web/
rm -rf $root/gongzuoshi-server/vue-web/dist/
mkdir -p $root/web/
cd $root/gongzuoshi-server/vue-web
rm -rf node_modules
npm install && webpack -p --progress
rsync -avzrut4P --progress --delete $root/gongzuoshi-server/vue-web/dist/ $root/web/
echo 'pack vue-web finish ...'
echo ''

# 打包vue-mobile文件
echo 'pack vue-mobile start ...'
rm -rf $root/mobile/
rm -rf $root/gongzuoshi-server/vue-mobile/dist/
mkdir -p $root/mobile/

cd $root/gongzuoshi-server/vue-mobile
rm -rf node_modules
mkdir -p node_modules
rsync -avzrutS4P --progress --delete $root/gongzuoshi-server/vue-web/node_modules/ $root/gongzuoshi-server/vue-mobile/node_modules/ 
webpack -p --progress
rsync -avzrutS4P --progress --delete $root/gongzuoshi-server/vue-mobile/dist/ $root/mobile/
rsync -avzrutS4P --progress --delete $root/gongzuoshi-server/vue-mobile/src/jweixin-1.1.0.js $root/mobile/
echo 'pack vue-mobile finish ...'
echo ''

# 打包vue-hot文件
echo 'pack vue-hot start ...'
rm -rf $root/hot/
rm -rf $root/gongzuoshi-server/vue-hot/dist/
rm -rf $root/gongzuoshi-server/vue-hot/node_modules/
mkdir -p $root/hot/
cd $root/gongzuoshi-server/vue-hot/
npm install vue-resource vue-router weixin-js-sdk && npm install && npm run build
cd $root/gongzuoshi-server/vue-hot/dist/
rsync -avzrutS4P --progress --delete $root/gongzuoshi-server/vue-hot/dist/ $root/hot/
echo 'pack vue-hot finish ...'

# 打包vue-mobile 2.0文件
echo 'pack vue-mobile 2.0 start ...'
rm -rf $root/mobile/
rm -rf $root/gongzuoshi-server/vue-mobile2.0/dist/
rm -rf $root/gongzuoshi-server/vue-mobile2.0/node_modules/
mkdir -p $root/mobile/
cd $root/gongzuoshi-server/vue-mobile2.0/
rsync -avzrutS4P --progress --delete $root/gongzuoshi-server/vue-hot/node_modules/ $root/gongzuoshi-server/vue-mobile2.0/node_modules/ 
npm run build
cd $root/gongzuoshi-server/vue-mobile2.0/dist/
rsync -avzrutS4P --progress --delete $root/gongzuoshi-server/vue-mobile2.0/dist/ $root/mobile/
echo 'pack vue-hot finish ...'


# 复制html文件出来
rm -rf $root/html/
mkdir -p $root/html/
rsync -avzrutS4P --progress --delete $root/gongzuoshi-server/vue-html/ $root/html/
 

# 编译nodejs
rm -rf $root/node_modules
rm -rf $root/gongzuoshi-server/node_modules/

rm -rf $root/etc/areacode.json
rm -rf $root/etc/province.json
rm -rf $root/etc/city.json
rm -rf $root/etc/pcd.plist
cp $root/gongzuoshi-server/gu-core/src/resources/areacode.json $root/etc/areacode.json
cp $root/gongzuoshi-server/gu-core/src/resources/province.json $root/etc/province.json
cp $root/gongzuoshi-server/gu-core/src/resources/city.json $root/etc/city.json
cp $root/gongzuoshi-server/gu-core/src/resources/pcd.plist $root/etc/pcd.plist

echo 'build server start ...'
rm -rf $root/gongzuoshi-server/package.json
cp $root/gongzuoshi-server/node-core/package.json $root/gongzuoshi-server/package.json
cd $root/gongzuoshi-server
npm install

cd $root/gongzuoshi-server
for dir in *
do
	tmp=$(echo $dir | grep 'node\-')
	if [ ! $tmp ]; then
		continue
	fi
	
	rm -rf $root/$tmp
	mkdir -p $root/$tmp
	
	startName=${tmp#*-}
	if [ $startName = "test" ] || [ $(echo $startName | grep '\-') ]; then
		continue;
	fi
	
	echo 'compile '$tmp' ...'
	#babel $tmp -d $root/$tmp/ --ignore html*
	rsync -av $root/gongzuoshi-server/$tmp/ $root/$tmp/
	if [ -d $root/gongzuoshi-server/$tmp/html ]; then
		mkdir -p $root/$tmp/html/
		rsync -av $root/gongzuoshi-server/$tmp/html/ $root/$tmp/html/
	fi
	if [ -f $root/gongzuoshi-server/$tmp/filer.config ]; then
		cp -rf $root/gongzuoshi-server/$tmp/filer.config $root/$tmp
	fi
	
	# 生成脚本
	className=${startName:0:1}
	mainClass='start'$(echo $className | tr '[a-z]' '[A-Z]')${startName:1}
	if [ -f $root/gongzuoshi-server/$tmp/$mainClass.js ];then
		mkdir -p $root/$tmp/log
		rm -rf /usr/bin/run-$startName
		echo "#!/bin/bash
cd $root/$tmp
echo '$startName start success'
pm2 reload $mainClass.js > /dev/null &
" > /usr/bin/run-$startName && chmod 777 /usr/bin/run-$startName
		yes | cp -rf /usr/bin/run-$startName $root/shell/
		echo run-$startName >> /usr/bin/run-all && chmod 777 /usr/bin/run-all
	fi
done

rm -rf $root/node_modules
rm -rf $root/node-test
rsync -av $root/gongzuoshi-server/node_modules/ $root/node_modules/
rm -rf $root/gongzuoshi-server/node_modules/
rm -rf $root/gongzuoshi-server/package.json
yes | cp -rf /usr/bin/run-all $root/shell/
echo 'build server finish ...'

# 复制rsa密钥文件
rm -rf /srv/etc/rsa
mkdir -p /srv/etc/rsa
rsync -avzrutS4P --progress $root/gongzuoshi-server/gu-core/src/resources/mobile.properties $root/etc/mobile.properties
rsync -avzrutS4P --progress --delete $root/gongzuoshi-server/gu-core/src/resources/rsa/ $root/etc/rsa/

# 复制cert文件
rm -rf /srv/etc/cert
mkdir -p /srv/etc/cert
rsync -avzrutS4P --progress $root/gongzuoshi-server/gu-core/src/resources/cert/ $root/etc/cert/

# 打包完之后，恢复webpack配置文件
cd $root/gongzuoshi-server
git checkout -- vue-web/webpack.config.js
git checkout -- vue-mobile/webpack.config.js

# 恢复爬虫配置文件
git checkout -- $root/gongzuoshi-server/node-crawler/config.js
 
# 生成html文件，为了文件哈希
rm -rf $root/web/index.html
rm -rf $root/web/main.css
rm -rf $root/web/main.js
rm -rf $root/web/vendor.js
curl --insecure https://192.168.1.110/upgather/makehtml

# 网站icon
rm -rf $root/web/favicon.ico
cp $root/gongzuoshi-server/vue-web/src/favicon.ico $root/web/favicon.ico

# 微信demo
rm -rf $root/demo
cp -rf $root/gongzuoshi-server/demo $root/demo

# 同步到外网测试机
host='test.gatherup.cc'
echo 'sync to '$host
rsync -avzrutS4P --progress --ignore-errors --delete --exclude=log --exclude=rsync.pass --exclude=*.sem --exclude=cnpmjs.org --exclude=ssl.sh --exclude=/config --exclude=node-test --exclude=gongzuoshi-server --exclude=/webApp --exclude=*.sql --exclude=*.php --exclude=build.sh --exclude=fab.sh --exclude=etc --exclude=region --exclude=nlp --exclude=*.sql --include=clearlog.sh --exclude=SmsUtil.sh --exclude=IsDeadSmsUtil.sh --password-file=/srv/rsync.pass /srv/ gongzuoshi@$host::gu

# 同步配置文件
echo 'sync config to '$host
rsync -avzrutS4P --progress --password-file=$root/rsync.pass $root/gongzuoshi-server/gu-core/src/resources/areacode.json gongzuoshi@$host::gu-etc
rsync -avzrutS4P --progress --password-file=$root/rsync.pass $root/gongzuoshi-server/gu-core/src/resources/province.json gongzuoshi@$host::gu-etc
rsync -avzrutS4P --progress --password-file=$root/rsync.pass $root/gongzuoshi-server/gu-core/src/resources/city.json gongzuoshi@$host::gu-etc
rsync -avzrutS4P --progress --password-file=$root/rsync.pass $root/gongzuoshi-server/gu-core/src/resources/pcd.json gongzuoshi@$host::gu-etc
rsync -avzrutS4P --progress --password-file=$root/rsync.pass $root/gongzuoshi-server/gu-core/src/resources/mobile.properties gongzuoshi@$host::gu-etc
rsync -avzrutS4P --progress --delete --password-file=$root/rsync.pass $root/gongzuoshi-server/gu-core/src/resources/rsa/ gongzuoshi@$host::gu-rsa
rsync -avzrutS4P --progress --delete --password-file=$root/rsync.pass $root/gongzuoshi-server/gu-core/src/resources/cert/ gongzuoshi@$host::gu-cert


rm -rf $root/shell
echo 'build gatherup server finish!'