import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

/**
 * GenITE.java
 * 
 * This program will implement if...then...else statements with Java Bytecode.
 * @author Jaylon Kiper,Elizabeth Fultz
 * @version 1.0
 * Compiler Project 2
 * CS322 - Compiler Construction
 * Fall 2021
 */
public class GenITE{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"ITEStatements", null, "java/lang/Object",null);
        
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
        	
        	//Implements statements with grade system and jump codes decide what comment you get.
        	mv.visitIntInsn(Opcodes.BIPUSH, 70);
        	mv.visitVarInsn(Opcodes.ISTORE, 1);
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitIntInsn(Opcodes.BIPUSH, 100);
        	Label l0 = new Label();
        	mv.visitJumpInsn(Opcodes.IF_ICMPGT, l0);
        	mv.visitLdcInsn("GREAT");
        	mv.visitVarInsn(Opcodes.ASTORE, 2);
        	Label l1 = new Label();
        	mv.visitJumpInsn(Opcodes.GOTO, l1);
        	mv.visitLabel(l0);
        	mv.visitFrame(Opcodes.F_APPEND,1, new Object[] {Opcodes.INTEGER}, 0, null);
        	mv.visitVarInsn(Opcodes.ILOAD, 1);
        	mv.visitIntInsn(Opcodes.BIPUSH, 60);
        	Label l2 = new Label();
        	mv.visitJumpInsn(Opcodes.IF_ICMPLT, l2);
        	mv.visitLdcInsn("GOOD");
        	mv.visitVarInsn(Opcodes.ASTORE, 2);
        	mv.visitJumpInsn(Opcodes.GOTO, l1);
        	mv.visitLabel(l2);
        	mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        	mv.visitLdcInsn("BAD");
        	mv.visitVarInsn(Opcodes.ASTORE, 2);
        	mv.visitLabel(l1);
        	mv.visitFrame(Opcodes.F_APPEND,1, new Object[] {"java/lang/String"}, 0, null);
        	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitVarInsn(Opcodes.ALOAD, 2);
        	mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        	mv.visitInsn(Opcodes.RETURN);
        	mv.visitMaxs(2, 3);
        	mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        //Name for writeFile is ITEStatements.
        writeFile(b,"ITEStatements.class");
        
        System.out.println("Done!");
    }//end main
}//end class
