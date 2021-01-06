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
	sipush 1
	sipush 1
	iand
	ifgt L01
	sipush 0
	goto L02
	L01:
	sipush 1
	L02:

	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	return
.end method
