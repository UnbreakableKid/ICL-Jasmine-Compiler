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
	sipush 5
	putfield ref_int/v I
	putfield frame_0/v0 Ljava/lang/Object;
	pop
	aload_3
	getfield frame_0/v0 Ljava/lang/Object;
	checkcast ref_int
	getfield ref_int/v I 

	sipush 1
	iadd
	dup
	aload_3
	getfield frame_0/v0 Ljava/lang/Object;
	checkcast ref_int
	swap
	putfield ref_int/v I
	aload_3
	getfield frame_0/sl Ljava/lang/Object;
	astore_3
	return
.end method
