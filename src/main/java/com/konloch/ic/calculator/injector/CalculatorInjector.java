package com.konloch.ic.calculator.injector;

import com.konloch.ic.calculator.Calculator;
import com.konloch.ic.classloader.InjectedClassLoader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Konloch
 * @since 10/15/2023
 */
public class CalculatorInjector implements CalculatorInjectorI
{
	@Override
	public Calculator inject()
	{
		//create new calc instance based off of java ASM, implementing the functions as needed
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		
		//define class
		cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "GeneratedCalculator", null, "com/konloch/ic/calculator/Calculator", null);
		{
			//implement init method
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/konloch/ic/calculator/Calculator", "<init>", "()V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
			
			//implement add method
			mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "add", "(JJ)J", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.LLOAD, 1);
			mv.visitVarInsn(Opcodes.LLOAD, 3);
			mv.visitInsn(Opcodes.LADD);
			mv.visitInsn(Opcodes.LRETURN);
			mv.visitMaxs(3, 3);
			mv.visitEnd();
			
			//implement sub method
			mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "sub", "(JJ)J", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.LLOAD, 1);
			mv.visitVarInsn(Opcodes.LLOAD, 3);
			mv.visitInsn(Opcodes.LSUB);
			mv.visitInsn(Opcodes.LRETURN);
			mv.visitMaxs(3, 3);
			mv.visitEnd();
			
			//implement mul method
			mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "mul", "(JJ)J", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.LLOAD, 1);
			mv.visitVarInsn(Opcodes.LLOAD, 3);
			mv.visitInsn(Opcodes.LMUL);
			mv.visitInsn(Opcodes.LRETURN);
			mv.visitMaxs(3, 3);
			mv.visitEnd();
			
			//implement div method
			mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "div", "(JJ)J", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.LLOAD, 1);
			mv.visitVarInsn(Opcodes.LLOAD, 3);
			mv.visitInsn(Opcodes.LDIV);
			mv.visitInsn(Opcodes.LRETURN);
			mv.visitMaxs(3, 3);
			mv.visitEnd();
		}
		cw.visitEnd();
		
		try
		{
			byte[] bytecode = cw.toByteArray();
			
			//define a custom class loader to load the generated class
			InjectedClassLoader classLoader = new InjectedClassLoader();
			
			Class<?> generatedClass = classLoader.defineClass("GeneratedCalculator", bytecode);
			return (Calculator) generatedClass.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}