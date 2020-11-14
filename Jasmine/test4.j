.class public test4
.super java/lang/Object

.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
.method public static main([Ljava/lang/String;)V
	.limit locals 4
	.limit stack 256

getstatic java/lang/System/out Ljava/io/PrintStream;
	new frame_0
	dup
	invokespecial frame_0/<init>()V
	dup
	aload_0
	putfield frame_0/sl Ljava/lang/Object;
	dup
	astore_3
	dup
	sipush 5
	putfield frame_0/v0 I
	dup
	sipush 3
	putfield frame_0/v1 I
	dup
	aload_3
	getfield frame_0/v0 I
	sipush 4
	iadd
	aload_3
	getfield frame_0/v1 I
	iadd
	putfield frame_0/v2 I
	pop
	aload_3
	getfield frame_0/v0 I
	aload_3
	getfield frame_0/v1 I
	iadd
	aload_3
	getfield frame_0/v2 I
	iadd

	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	return
.end method
