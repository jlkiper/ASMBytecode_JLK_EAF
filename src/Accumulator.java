import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

/**
 * Accumulator.java
 * 
 * This program will get input(I or D), from the user, run a for loop that
 * adds that number to an accumulator and then prints the results in Java Bytecode.
 * @author Jaylon Kiper,Elizabeth Fultz
 * @version 1.0
 * Compiler Project 2
 * CS322 - Compiler Construction
 * Fall 2021
 */
public class Accumulator{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"Accumulate", null, "java/lang/Object",null);
        
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
		
		//Accumulates integers
		mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
		mv.visitInsn(Opcodes.DUP);
		mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
		mv.visitVarInsn(Opcodes.ASTORE, 1);
		mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("Enter an Integer: ");
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
		mv.visitVarInsn(Opcodes.ISTORE, 2);
		mv.visitInsn(Opcodes.ICONST_0);
		mv.visitVarInsn(Opcodes.ISTORE, 3);
		mv.visitInsn(Opcodes.ICONST_0);
		mv.visitVarInsn(Opcodes.ISTORE, 4);
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitFrame(Opcodes.F_FULL, 5, new Object[] {"[Ljava/lang/String;", "java/util/Scanner", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER}, 0, new Object[] {});
		mv.visitVarInsn(Opcodes.ILOAD, 4);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		Label l1 = new Label();
		mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1);
		mv.visitVarInsn(Opcodes.ILOAD, 3);
		mv.visitVarInsn(Opcodes.ILOAD, 4);
		mv.visitInsn(Opcodes.IADD);
		mv.visitVarInsn(Opcodes.ISTORE, 3);
		mv.visitIincInsn(4, 1);
		mv.visitJumpInsn(Opcodes.GOTO, l0);
		mv.visitLabel(l1);
		mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitVarInsn(Opcodes.ILOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
		
		//Accumulates doubles
		mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("Enter a Double: ");
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextDouble", "()D", false);
		mv.visitVarInsn(Opcodes.DSTORE, 5);
		mv.visitInsn(Opcodes.DCONST_0);
		mv.visitVarInsn(Opcodes.DSTORE, 7);
		mv.visitInsn(Opcodes.ICONST_0);
		mv.visitVarInsn(Opcodes.ISTORE, 9);
		Label l2 = new Label();
		mv.visitLabel(l2);
		mv.visitFrame(Opcodes.F_APPEND,3, new Object[] {Opcodes.DOUBLE, Opcodes.DOUBLE, Opcodes.INTEGER}, 0, null);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitInsn(Opcodes.I2D);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitInsn(Opcodes.DCMPG);
		Label l3 = new Label();
		mv.visitJumpInsn(Opcodes.IFGE, l3);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitInsn(Opcodes.I2D);
		mv.visitInsn(Opcodes.DADD);
		mv.visitVarInsn(Opcodes.DSTORE, 7);
		mv.visitIincInsn(9, 1);
		mv.visitJumpInsn(Opcodes.GOTO, l2);
		mv.visitLabel(l3);
		mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(4, 10);
		mv.visitEnd();
		}
       	cw.visitEnd();

        byte[] b = cw.toByteArray();
        
        //Name for this writeFile is Accumulate.
        writeFile(b,"Accumulate.class");
        
        System.out.println("Done!");
    }//end main
}//end class

