namespace java com.share.soa.thrift.protocol

struct  User{
	1:i32 id1;
	2:i16 id2;
	3:string content;
	4:string mediaFrom;
	5:string author;
}

#注释 ...
service Userf {
	i32 getUser(1:i32 a),
	string getUser2(1:string name)
}