package main;

import java.awt.Color;


public enum ColorEnum {
		BLACK("BLACK",new Color(0,0,0)),
		RED("RED", new Color(255,0,0)),
		GREEN("GREEN", new Color(0,255,0)),
		BLUE("BLUE", new Color(0,0,255)),
		WHITE("WHITE",new Color(255,255,255)),
		YELLOW("YELLOW",new Color(255,255,0)),
		ORANGE("ORANGE",new Color(255,165,0)),
		GREY("GREY",new Color(169,169,169));
		
		private final String name;
		private final Color color;
		
		private ColorEnum(String name, Color color) {
			this.name = name;
			this.color = color;
		}
		
		public String getName() {
			return name;
		}
		
		public Color getColor() {
			return color;
		}
		
		public static Color getColor(String color) {
			return ColorEnum.valueOf(color).getColor();
		}
		
	}