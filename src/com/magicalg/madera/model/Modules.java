package com.magicalg.madera.model;

public class Modules {
	private Integer idChoixModule;
	private ModuleModel moduleA;
	private ModuleModel moduleB;
	private String typeAngle;
	private Integer angle;
	
	public Integer getIdChoixModule() {
		return idChoixModule;
	}
	public void setIdChoixModule(Integer idChoixModule) {
		this.idChoixModule = idChoixModule;
	}
	
	public ModuleModel getModuleA() {
		if (null == moduleA){
			moduleA = new ModuleModel();
		}
		return moduleA;
	}
	public void setModuleA(ModuleModel moduleA) {
		this.moduleA = moduleA;
	}
	public ModuleModel getModuleB() {
		if (null == moduleB){
			moduleB = new ModuleModel();
		}
		return moduleB;
	}
	public void setModuleB(ModuleModel moduleB) {
		this.moduleB = moduleB;
	}
	public String getTypeAngle() {
		return typeAngle;
	}
	public void setTypeAngle(String typeAngle) {
		this.typeAngle = typeAngle;
	}
	public Integer getAngle() {
		return angle;
	}
	public void setAngle(Integer angle) {
		this.angle = angle;
	}
	@Override
	public String toString() {
		return "Modules [idChoixModule=" + idChoixModule + ", moduleA=" + moduleA + ", moduleB=" + moduleB
				+ ", typeAngle=" + typeAngle + ", angle=" + angle + "]";
	}
	
	
	

}
