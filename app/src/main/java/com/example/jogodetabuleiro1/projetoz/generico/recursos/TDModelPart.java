package com.example.jogodetabuleiro1.projetoz.generico.recursos;

import java.util.Vector;

public class TDModelPart {

	Material material;

	
	public TDModelPart(  Material material ) {
		super();

		this.material = material;

	}
	public String toString(){
		String str=new String();
		if(material!=null)
			str+="Material name:"+material.getName();
		else
			str+="Material not defined!";

		return str;
	}

	private static short[] toPrimitiveArrayS(Vector<Short> vector){
		short[] s;
		s=new short[vector.size()];
		for (int i=0; i<vector.size(); i++){
			s[i]=vector.get(i);
		}
		return s;
	}
 	public Material getMaterial(){
		return material;
	}
	
	
}
