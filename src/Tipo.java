

	
public enum Tipo {
	
	Tecnologia(1) {
		public String toString() {
			return "Tecnologia";
		}
	},
	Ciencia(2) {
		public String toString() {
			return "Ciencia";
		}
	},
	Comida(3) {
		public String toString() {
			return "Comida";
		}
	},
	Bebida(4) {
		public String toString() {
			return "Bebida";
		}
	},
	Domestico(5) {
		public String toString() {
			return "Domestico";
		}
	},
	Outros(6) {
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
