package domain;

import java.util.Optional;

public class FoodManager {

	private static FoodList list = new FoodList();
	
	static {
		list.add(new Food("Onion", 20, "2 slices"));
		list.add(new Food("Beans", 60, "1 cup"));
		list.add(new Food("Orange", 80, "1 whole"));
		list.add(new Food("Cereal", 120, "1 cup"));
	}
	
	public static FoodList getFoods() {
		return list;
	}
	
	public static Food find(String name) {
		final Optional<Food> foundFood = 
				list.stream().filter(food -> food.getName().equalsIgnoreCase(name)).findFirst();
		return foundFood.orElse(null);
	}
	
	public static void addFood(Food food) {
		list.add(food);
	}
	
	public static boolean update(Food food) {
		Food res = find(food.getName());
		if (res==null) {
			return false;
		}
		for (Food f : list) {
			if (f.getName().equalsIgnoreCase(food.getName())) {
				f.setCalories(food.getCalories());
				f.setServingSize(food.getServingSize());
				return true;
			}
		}
		return false;
	}
	
	public static void delete(String name) {
		Food food = find(name);
		list.remove(food);
	}
}
