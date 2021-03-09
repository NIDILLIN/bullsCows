import java.util.* ;

// TO-DO LIST
// 1. Сделать проверку на количество цифр, введенных пользаком ( поймать exception )
// 2. Создать метод array.compare()
//    Он проверяет сходятся ли массивы полностью,
//    А также создает массив быков и коров ( соот. [0] и [1] )
//    Быки коровы есть атрибут класса
// 3. При ходе игрока, выводистя слева случайная надписьи разбивается построччно на три строки
// 
// 

class programm
{
	static boolean notFullCompared = true; // Массивы полностью не сопадают
	static String Input;
	static Scanner in = new Scanner(System.in);
	static int k;
	static boolean cheat;

	public static int[] bullscows;
	static String[] secretMass;    // Генерация секретных чисел
	static String[] secretSymbs;   // Генерация случайных букв англ. алфавита


	public static void main(String[] args) {
		

		if (args.length == 1 && args[0].equals("visChea"))
		{
			cheat = true; // Читы, если включен, отображает отгаданные числа на своей позиции
			msg("cheat activated");
		}

		msg("==========================================");
		msg("Добро пожаловать в игру \"Быки и Коровы\"!");
		msg("==========================================");
		msg("Было загадано 4-х значное число, сможете ли вы его угадать?");
		msg("");
		msg("No lines", "Что дальше?  (Tutorial--t) (Start--s) (End--e)  ");
		Input = in.next();

		genArrs();
		StartCicle(); // Ожидает от пользака символа s
		TutorialCicle(); // Ожидает "t"

		msg("Желаем удачи!");
	};



	public static void TutorialCicle()
	{
		String[] a = {"1", "2", "3", "1"}; //
		String[] b = {"A","B","C","D"};    // Tutorial
		String[] c = {" ","^","^"," "};    // Game
	 	String[] d = {" ","v","v"," "};    // Helpers

		while(Input.equalsIgnoreCase("t") || Input.equals("1")) // Tutorial part, обучаеющее место
		{
			msg("=====================================================================");
			array.show(d, "?", "tutor"); msg("");
			array.show(a, "?"); msg("No lines", " Это числа, которые ты ввел"); msg("");
			array.show(b, "*"); msg("No lines", " Это загадка, то, где будут видны быки и коровы"); msg("");
			array.show(c, "*", "tutor"); msg("");
			msg("=====================================================================");

			msg("No lines", "Что дальше?  (Tutorial--t) (Start--s) (End--e)  ");
			Input = in.next();
			if (Input != "t" || Input != "1") StartCicle();
		}
	};

	public static void StartCicle() // Бесконечный цикл пока будет нажиматься кнопка s
	{	

		while(Input.equalsIgnoreCase("s") || Input.equals("2"))
		{

			if (notFullCompared == true) // if true
			{
				k++;
				msg("No lines", "Введите число разгадку: (?) ");
				String[] inputPolz = array.input(); // Ввод массива для разгадки
				System.out.printf("=================--%d--======================", k); msg("");

				//===============SHOW_ROOM================
				array.show(inputPolz, "?"); msg(""); 
				if (!cheat) msg(""); // Если чит не введен -- пустая строка
				array.show(secretSymbs, "*"); msg(""); 

				if(cheat) {array.show(secretMass, "^"); msg("");} 	 // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//===============SHOW_ROOM================

				// notFullCompared = false;
				// array.compare(inputPolz, secretMass); // Сравнение элементов массива с загаданными

				msg("==========================================");
				StartCicle();

				
			} else 
			{
				msg("==========================================");
				msg("No lines","Молодцы! Партия выйграна за "); msg("No lines", k); msg(" ходов!");
				msg("==========================================");
				msg("==========================================");
				msg("No lines", "Что дальше?  (Tutorial--t) (Start--s) (End--e)  ");
				Input = in.next();

				if (Input.equalsIgnoreCase("s")) 
				{
					notFullCompared = true; 
					genArrs();
					deleteBullsCows();
					k = 0;
				}
			}
			
		}
	};

	public static void genArrs()
	{
		secretMass = array.secretRandomArr(); // Генерация секретных чисел
		secretSymbs = array.randomSymbStringArr(); // Генерация случайных букв англ. алфавита
	};

	public static void deleteBullsCows()
	{
		bullscows[0] = 0; // Быков стало ноль
		bullscows[1] = 0; // Коров стало ноль
	};

	public static void msg(String message) // Вывод сообщения на экран консоли
	{
		System.out.println(message);
	};

	public static void msg(String noLines, String message) // Вывод сообщения на экран консоли (!) без новой строки
	{	
		System.out.print(message);
	};

	// int числа 
	public static void msg(String noLines, int message) // Вывод сообщения на экран консоли (!) без новой строки
	{	
		System.out.print(message);
	};

	public static void msg(int message) // Вывод сообщения на экран консоли
	{
		System.out.println(message);
	};

}


class array
{
	// public static void compare(String[] array, String[] arraySecret) //Сравнивает вводимый массив с загаданным (IN == RandArr?)
	// {
	// 	if (for(int i = 0; i < 4; i++))
	// 	{
	// 		programm.notFullCompared = false;
	// 	}
	// }

	public static void show(String[] array, String ask) // Показывает массив из 4-х символов, БЕЗ ПЕРЕНОСА СТРОКИ
	{													// Работает также для разгадки и загадки одновременно без условия
		//ask = "?" or = "*"							// Что повышает производительность

		System.out.printf("-%s%s- ", ask, ask);

		for (int i = 0; i < 4; i++)
			System.out.print(array[i] + " "); // Массив разгадка помечен вопросительным знаком
											  // Массив загадка помечен звездочкой
		System.out.printf("-%s%s-  ", ask, ask);
	};

	public static void show(String[] array, String ask, String tutor) // Показывает массив ( перегруженный, для туториала )
	{													// Аргумент ни на что не влияет, нужен только для перегрузки метода
		System.out.printf("---- ");
		for (int i = 0; i < 4; i++)
			System.out.print(array[i] + " "); 
		System.out.printf("---- ");		  
	};

	public static String[] input() //Ввод пользаком нового массива для разгадки
	{
		String Input[] = new String[4];
		String numsInput;
		Scanner in = new Scanner(System.in);

		numsInput = in.next().toUpperCase(); // Строка с числами, которая в последствии должна быть разъединена

		if (numsInput.length() == 3) 
		{
				for (int i = 0; i < 3; i++) // Проверка, содержит ли строка их 3-х сиволов
				{							// Необходимые "VIS". Это далее -->
					Input[i] = numsInput.substring(i, i + 1);
				}

				if (Input[0].equalsIgnoreCase("v") && Input[1].equalsIgnoreCase("i") && Input[2].equalsIgnoreCase("s"))
				{   // Сама проверка
					if (programm.cheat == true) 
					{
						Input[0] = "F";
						Input[1] = "U";
						Input[2] = "C";
						Input[3] = "K";
					} else
					{
						programm.cheat = true;
						Input[0] = " ";
						Input[1] = "V";
						Input[2] = "I";
						Input[3] = " ";
					}
				}
		}
		else {
			for (int i = 0; i < 4; i++) // Заполнение массива числами. Не более 4-х
				Input[i] = numsInput.substring(i, i + 1); // i-й элемент массива есть строка-символ b
		}

		return Input;

	};

	public static String[] randomSymbStringArr() // Создает массив из различных цифр-символов по таблице ASCII (рандомно)
	{
		String[] symbols = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String[] newArray = new String[4];

		for (int i = 0; i < 4; i++)
			newArray[i] = symbols[(int) (Math.random() * 26)]; // 26 -- symbols.length
			// newArray[i] = symbols[0 + ((int) Math.random() * 24)];

		return newArray;
	};

	public static String[] secretRandomArr() // Создает массив из различных цифр-символов по таблице ASCII (рандомно)
	{
		String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] newArray = new String[4];

		for (int i = 0; i < 4; i++)
			newArray[i] = nums[(int) (Math.random() * 10)]; // 10 -- nums.length

		return newArray;
	};
}