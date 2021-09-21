import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

/**
 * GenCompNum.java
 * 
 * This program will compare two numbers (I,L, and D) to determine which is bigger/smaller with Java Bytecode.
 * @author Jaylon Kiper,Elizabeth Fultz
 * @version 1.0
 * Compiler Project 2
 * CS322 - Compiler Construction
 * Fall 2021
 */
public class GenCompNum{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"CompNumbers", null, "java/lang/Object",null);
        
        {
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}

        {
        	MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        	mv.visitCode();
        	
        	//Comparing two integers by pushing them into labels and checking for greater than,
        	//less than or equal
        	mv.visitIntInsn(Opcodes.BIPUSH, 56);
        	mv.visitVarInsn(Opcodes.ISTORE, 1);
        	mv.visitIntInsn(Opcodes.BIPUSH, 100);
        	mv.visitVarInsn(Opcodes.ISTORE, 2);
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitVarInsn(Opcodes.ILOAD, 2);
        	Label l0 = new Label();
        	mv.visitJumpInsn(Opcodes.IF_ICMPGE, l0);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.ILOAD, 2);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        	mv.visitLabel(l0);
        	mv.visitFrame(Opcodes.F_APPEND,2, new Object[] {Opcodes.INTEGER, Opcodes.INTEGER}, 0, null);
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitVarInsn(Opcodes.ILOAD, 2);
        	Label l1 = new Label();
        	mv.visitJumpInsn(Opcodes.IF_ICMPLE, l1);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        	mv.visitLabel(l1);
        	mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitVarInsn(Opcodes.ILOAD, 2);
        	Label l2 = new Label();
        	mv.visitJumpInsn(Opcodes.IF_ICMPNE, l2);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("They are Equal");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitLabel(l2);
        	mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        	
        	//Comparing two long numbers by pushing them into labels and checking for greater than,
        	//less than or equal
        	mv.visitLdcInsn((Long)200000L);
        	mv.visitVarInsn(Opcodes.LSTORE, 3);
        	mv.visitLdcInsn((Long)34567L);
        	mv.visitVarInsn(Opcodes.LSTORE, 5);
        	mv.visitVarInsn(Opcodes.LLOAD, 3);
        	mv.visitVarInsn(Opcodes.LLOAD, 5);
        	mv.visitInsn(Opcodes.LCMP);
        	Label l3 = new Label();
        	mv.visitJumpInsn(Opcodes.IFGE, l3);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.LLOAD, 5);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
        	mv.visitLabel(l3);
        	mv.visitFrame(Opcodes.F_APPEND,2, new Object[] {Opcodes.LONG, Opcodes.LONG}, 0, null);
        	mv.visitVarInsn(Opcodes.LLOAD, 3);
        	mv.visitVarInsn(Opcodes.LLOAD, 5);
        	mv.visitInsn(Opcodes.LCMP);
        	Label l4 = new Label();
        	mv.visitJumpInsn(Opcodes.IFLE, l4);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.LLOAD, 3);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
        	mv.visitLabel(l4);
        	mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        	mv.visitVarInsn(Opcodes.LLOAD, 3);
        	mv.visitVarInsn(Opcodes.LLOAD, 5);
        	mv.visitInsn(Opcodes.LCMP);
        	Label l5 = new Label();
        	mv.visitJumpInsn(Opcodes.IFNE, l5);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("They are Equal");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitLabel(l5);
        	mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        	
        	//Comparing two doubles by pushing them into labels and checking for greater than,
        	//less than or equal
        	mv.visitLdcInsn((Double)60.0);
        	mv.visitVarInsn(Opcodes.DSTORE, 7);
        	mv.visitLdcInsn((Double)60.0);
        	mv.visitVarInsn(Opcodes.DSTORE, 9);
        	mv.visitVarInsn(Opcodes.DLOAD, 7);
        	mv.visitVarInsn(Opcodes.DLOAD, 9);
        	mv.visitInsn(Opcodes.DCMPG);
        	Label l6 = new Label();
        	mv.visitJumpInsn(Opcodes.IFGE, l6);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.DLOAD, 9);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
        	mv.visitLabel(l6);
        	mv.visitFrame(Opcodes.F_APPEND,2, new Object[] {Opcodes.DOUBLE, Opcodes.DOUBLE}, 0, null);
        	mv.visitVarInsn(Opcodes.DLOAD, 7);
        	mv.visitVarInsn(Opcodes.DLOAD, 9);
        	mv.visitInsn(Opcodes.DCMPL);
        	Label l7 = new Label();
        	mv.visitJumpInsn(Opcodes.IFLE, l7);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.DLOAD, 7);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
        	mv.visitLabel(l7);
        	mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        	mv.visitVarInsn(Opcodes.DLOAD, 7);
        	mv.visitVarInsn(Opcodes.DLOAD, 9);
        	mv.visitInsn(Opcodes.DCMPL);
        	Label l8 = new Label();
        	mv.visitJumpInsn(Opcodes.IFNE, l8);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("They are Equal");
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitLabel(l8);
        	mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        	mv.visitInsn(Opcodes.RETURN);
        	mv.visitMaxs(4, 11);
        	mv.visitEnd();
        	}
        	cw.visitEnd();

        byte[] b = cw.toByteArray();

        //Name of writeFile is CompNumbers
        writeFile(b,"CompNumbers.class");
        
        System.out.println("Done!");
    }//end main
}//end class
