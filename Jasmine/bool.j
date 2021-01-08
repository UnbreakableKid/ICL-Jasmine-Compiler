.class public bool
.super java/lang/Object

.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
.method public static main([Ljava/lang/String;)V
	.limit locals 10
	.limit stack 256

	aconst_null
	astore_3

	new frame_0
	dup
	invokespecial frame_0/<init>()V
	dup
	aload_0
	putfield frame_0/sl Ljava/lang/Object;
	dup
	astore_3
	dup
	new ref_int
	dup
	invokespecial ref_int/<init>()V
	dup
	sipush 1
	putfield ref_int/v I
	putfield frame_0/v0 Ljava/lang/Object;
	dup
	new ref_int
	dup
	invokespecial ref_int/<init>()V
	dup
	sipush 10
	putfield ref_int/v I
	putfield frame_0/v1 Ljava/lang/Object;
	pop
	L0:
	aload_3
	getfield frame_0/v1 Ljava/lang/Object;
	checkcast ref_int
	getfield ref_int/v I 

	aload_3
	getfield frame_0/v0 Ljava/lang/Object;
	checkcast ref_int
	getfield ref_int/v I 

	if_icmpgt	TRUE2
	sipush 0
	goto exit2
	TRUE2:
	sipush 1
	exit2:
	ifeq L01
	aload_3
	getfield frame_0/v1 Ljava/lang/Object;
	checkcast ref_int
	getfield ref_int/v I 

	dup
	getstatic java/lang/System/out Ljava/io/PrintStream;
	swap
	invokevirtual java/io/PrintStream/println(I)V
	pop
	aload_3
	getfield frame_0/v1 Ljava/lang/Object;
	checkcast ref_int
	getfield ref_int/v I 

	sipush 2
	idiv
	sipush 0
	if_icmpeq	TRUE6
	sipush 0
	goto exit6
	TRUE6:
	sipush 1
	exit6:
	ifgt L31
	sipush 3
	aload_3
	getfield frame_0/v1 Ljava/lang/Object;
	checkcast ref_int
	getfield ref_int/v I 

	imul
	sipush 1
	iadd
	dup
	aload_3
	getfield frame_0/v1 Ljava/lang/Object;
	checkcast ref_int
	swap
	putfield ref_int/v I
	goto L32
	L31:
	aload_3
	getfield frame_0/v1 Ljava/lang/Object;
	checkcast ref_int
	getfield ref_int/v I 

	sipush 2
	idiv
	dup
	aload_3
	getfield frame_0/v1 Ljava/lang/Object;
	checkcast ref_int
	swap
	putfield ref_int/v I
	L32:
	pop
	goto L0
	L01:
	aload_3
	getfield frame_0/sl Ljava/lang/Object;
	astore_3
	return
.end method
