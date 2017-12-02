package domain;

import java.util.Optional;

public class FoodManager {
	
	private static FoodList list = new FoodList();
	
	static {
		list.add(new Food("onion", 20, "2 slices"));
		list.add(new Food("Beans", 60, "1 cup"));
		list.add(new Food("Orange", 80, "1 whole"));
		list.add(new Food("Cereal", 120, "1 cup"));
	}
	
	public static FoodList getFoods() {
		return list;
	}
	
	public static Food find(String name) {
		final Optional<Food> foundFood = list.stream()
				.filter(food -> food.getName().equalsIgnoreCase(name)).findFirst();
		return foundFood.orElse(null);
	}
	
	public static void add(Food food) {
		list.add(food);
	}
	
	public static Food update(Food food) {
		if (find(food.getName())==null) {
			return null;
		}
		for (Food f : list) {
			if (f.getName().equalsIgnoreCase(food.getName())) {
				f.setName(food.getName());
				f.setCalories(food.getCalories());
				f.setServingSize(food.getServingSize());
				return f;
			}
		}
		return null;
	}
	
	public static void delete(String name) {
		Food food = find(name);
		list.remove(food);
	}

}
