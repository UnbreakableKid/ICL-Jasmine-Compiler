.class public bool
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
	sipush 4
	putfield frame_0/v0 I
	pop
L0:
	aload_3
	getfield frame_0/v0 I
	sipush 3
	if_icmpgt	TRUE2
	sipush 0
	goto exit2
TRUE2:
	sipush 1
exit2:
	ifeq L01
	aload_3
	getfield frame_0/v0 I
	sipush 1
	isub
	pop
	goto L0
L01:
	pop
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload_3
	getfield frame_0/v0 I
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	aload_3
	getfield frame_0/sl Ljava/lang/Object;
	astore_3
	return
.end method
