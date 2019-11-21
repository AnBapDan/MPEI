

	
public enum Tipo {
	
	Tecnologia(0) {
		public String toString() {
			return "Tecnologia";
		}
	},
	Ciencia(1) {
		public String toString() {
			return "Ciencia";
		}
	},
	Comida(2) {
		public String toString() {
			return "Comida";
		}
	},
	Bebida(3) {
		public String toString() {
			return "Bebida";
		}
	},
	Domestico(4) {
		public String toString() {
			return "Domesticos";
		}
	},
	Outros(5) {
		public String toString() {
			return "Outros";
		}
	};
	
	private int val;
	
	Tipo(int val) {
		this.val = val;
	}
	public int getVal() {
		return val;
	}

	

}
