.class public Demo
.super java/lang/Object

;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals  4
       .limit stack 256

       ; setup local variables:

       ;    1 - the PrintStream object held in java.lang.System.out
       getstatic java/lang/System/out Ljava/io/PrintStream;

       ; place your bytecodes here
       ; START

         new frame_0
         dup
         invokespecial frame_0/<init>()V
         dup
         putfield frame_0/sl Ljava/lang/Object;
         dup
         astore_3
         dup
         sipush 2
         putfield frame_0/v0 I
         dup
         sipush 3
         putfield frame_0/v1 I
         pop
         new frame_1
         dup
         invokespecial frame_1/<init>()V
         dup
         aload_3
         putfield frame_1/sl Lframe_0;
         dup
         astore_3
         dup
         aload_3
         getfield frame_1/sl Lframe_0;
         getfield frame_0/v0 I
         aload_3
         getfield frame_1/sl Lframe_0;
         getfield frame_0/v1 I
         iadd
         putfield frame_1/v0 I
         pop
         aload_3
         getfield frame_1/sl Lframe_0;
         getfield frame_0/v0 I
         aload_3
         getfield frame_1/sl Lframe_0;
         getfield frame_0/v1 I
         iadd
         aload_3
         getfield frame_1/v0 I
         iadd
         aload_3
         getfield frame_1/sl Lframe_0;
         astore_3
         aload_3
         getfield frame_0/sl Ljava/lang/Object;
         astore_3


       ; END


       ; convert to String;
       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
       ; call println 
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

       return

.end method
