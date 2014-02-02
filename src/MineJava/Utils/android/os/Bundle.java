package MineJava.Utils.android.os;

public class Bundle extends Object{

	private String[] strings;
	private String[] stringKeys;
	private int[] ints;
	private String[] intKeys;

	public Bundle putInt(String name, int oneInt){
		int l=ints.length;
		ints[l]=oneInt;
		intKeys[l]=name;
		return this;
	}
	public int getInt(String name, int defaultValue){
		for(int i=0;i<ints.length;i++){
			if(intKeys[i]==name)
				return ints[i];
		}
		return defaultValue;
	}
	public Bundle putString(String name, String string){
		int l=strings.length;
		strings[l]=string;
		stringKeys[l]=name;
		return this;
	}
	public String getString(String name, String defaultValue){
		for(int i=0;i<strings.length;i++){
			if(stringKeys[i]==name)
				return strings[i];
		}
		return defaultValue;
	}
	

}
