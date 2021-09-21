import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

/**
 * WhileLoop.java
 * This program will implement a While loop with Java Bytecode.
 * @author Jaylon Kiper,Elizabeth Fultz
 * @version 1.0
 * Compiler Project 2
 * CS322 - Compiler Construction
 * Fall 2021
 */
public class WhileLoop{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"LoopWhile", null, "java/lang/Object",null);
        
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
        	
        	//Implements while loop with constants and jump instructions.
        	//Frames also create the stack map table for increments.
         	mv.visitInsn(Opcodes.ICONST_0);
        	mv.visitVarInsn(Opcodes.ISTORE, 1);
        	Label l0 = new Label();
        	mv.visitLabel(l0);
        	mv.visitFrame(Opcodes.F_APPEND,1, new Object[] {Opcodes.INTEGER}, 0, null);
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitInsn(Opcodes.ICONST_5);
        	Label l1 = new Label();
        	mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1);
        	mv.visitIincInsn(1, 1);
        	mv.visitJumpInsn(Opcodes.GOTO, l0);
        	mv.visitLabel(l1);
        	mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        	
        	mv.visitInsn(Opcodes.RETURN);
        	mv.visitMaxs(2, 2);
        	mv.visitEnd();
        	}
        	cw.visitEnd();

        byte[] b = cw.toByteArray();

        //Name for writeFile is LoopWhile.
        writeFile(b,"LoopWhile.class");
        
        System.out.println("Done!");
    }//end main
}//end class
