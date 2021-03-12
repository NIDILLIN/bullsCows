import java.util.* ;
// CHEAT vis -- позволяет видеть загаданный массив чисел
// CHEAT sv -- будет позволять увидеть числа на своих местах
// 
// TO-DO LIST
// 
// 1. При ходе игрока, выводистя слева случайная надписьи разбивается построччно на три строки
// 
// 2. Проверить, если в INPUT два одинаковых числа ( бык - 1 )!!!!
//  А еще это должно быть связано с коровами
//  Почему ж в игропроме все всегда через жопу, через костыли-то?

class programm
{
	static boolean arrsNotFullCompared = true; // Массивы полностью не сопадают
	static boolean cowExists = false; // Коровы не существуют
	static boolean bullExists = false; // быки не существуют
	static String Input;
	static Scanner in = new Scanner(System.in);
	static int k;
	// ======Cheats======
	static boolean cheatVis; // Чит отладки
	static boolean cheatSv;  // Чит на упрощение прохождения, вместо букв,
							 // в третьей строчке будут выбиваться отгаданные числа
	// ======Cheats======
	public static int[] bullscows = {0, 0};
	static String[] secretMass;    // Генерация секретных чисел
	static String[] secretSymbs;   // Генерация случайных букв англ. алфавита


	public static void main(String[] args) {
		

		if (args.length == 1 && args[0].equals("visChea"))
		{
			cheatVis = true; // Читы, если включен, отображает отгаданные числа на своей позиции
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
		String[] b = {"A","3","1","D"};    // Tutorial
		String[] c = {" ","^","^"," "};    // Game
	 	String[] d = {" ","v","v"," "};    // Helpers

		while(Input.equalsIgnoreCase("t") || Input.equals("1")) // Tutorial part, обучаеющее место
		{
			msg("=====================================================================");
			msg("Суть игры состоит в следующем: "); msg("");
			msg("Игрок вводит комбинации одну за другой, пока не отгадает всю последовательность.");
			msg("");
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

			if (arrsNotFullCompared == true) // if true
			{
				k++;
				msg("No lines", "Введите число разгадку: (?) ");
				String[] inputPolz = array.input(); // Ввод массива для разгадки
				System.out.printf("=================--%d--======================", k); msg("");
				array.compare(inputPolz, secretMass); // Сравнение элементов массива с загаданными

				//===============SHOW_ROOM================
				array.show(inputPolz, "?"); msg(""); 
				if (!cheatVis) msg(""); // Если чит не введен -- пустая строка
				array.show(secretSymbs, "*"); msg(""); 

				if(cheatVis) {array.show(secretMass, "^"); msg("");} 	 
				// System.out.println(secretMass[2]);
				
				//===============SHOW_ROOM================

				msg("============================================");
				StartCicle();

				
			} else 
			{
				msg("==========================================");
				msg("No lines","Молодцы! Партия выйграна за "); msg("No lines", k); msg(" шаг(а)(ов)!");

				arrsNotFullCompared = true; 
				genArrs();
				deleteBullsCows();
				k = 0;
				cheatVis = false;

				msg("==========================================");
				msg("==========================================");
				msg("No lines", "Что дальше?  (Tutorial--t) (Start--s) (End--e)  ");
				Input = in.next();

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
	public static void compare(String[] arrayInput, String[] arraySecret) //Сравнивает вводимый массив с загаданным (IN == RandArr?)
	{
		boolean fullCompared = false;
		boolean[] itersCompare = new boolean[4];
		boolean[] bullsIters = new boolean[4];

		programm.bullscows[0] = 0; // При вызове метода обнуляем быков
		programm.bullscows[1] = 0; // и коров

		for (int i = 0; i < 4; i++)
		{
			if ( arrayInput[i].equals(arraySecret[i]) ) 
			{
				itersCompare[i] = true; 
				if (itersCompare[i] == true)
					programm.bullscows[1] += 1; // Если числа совпадают на позициях -- корова++

			}
			for ( int it = 0; it < 4; it++ ) // пробег числа для выявления быков
			{ 
				if ( arrayInput[i].equals(arraySecret[it]) ) 
				{
					bullsIters[i] = true; programm.bullscows[0] += 1; 
				}
			
			}	
		}


		if (programm.bullscows[0] >= 4) programm.bullscows[0] = Math.round(programm.bullscows[0] / 2 + 2);
		// если коров больше 3-х то быки -3
		// Если два числа в Input одинаковы то быки -1
		if (programm.bullscows[1] > 0) programm.cowExists = true; // коровы существуют
		if (programm.bullscows[0] > 0) programm.bullExists = true; // быки существуют



		if (itersCompare[0] == true && itersCompare[1] == true && itersCompare[2] == true && itersCompare[3] == true)
			fullCompared = true; // Все числа совпадают

		if (fullCompared) 
		{
			programm.arrsNotFullCompared = false; // Если совпадают, то (неполное_совпадение) false
		}
		else
		{
			programm.arrsNotFullCompared = true;
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void show(String[] array, String ask) // Показывает массив из 4-х символов, БЕЗ ПЕРЕНОСА СТРОКИ
	{													// Работает также для разгадки и загадки одновременно без условия
		//ask = "?" or = "*"							// Что повышает производительность

		// Если быки / коровы существуют, то надо их показать
		if (ask.equals("*") && programm.cowExists == true) array[2] = Integer.toString(programm.bullscows[1]); // Вывод в буквы кол-во коров
		if (ask.equals("*") && programm.bullExists == true) array[1] = Integer.toString(programm.bullscows[0]); // Вывод в буквы кол-во быков
		
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

	public static boolean itsThatWord(String input, String word) // Метод поиска читов в поле ввода
	{
		if ( input.equalsIgnoreCase(word) ) 
		{ 
			return true;
		}
		else return false;
	};				

	public static String[] input() //Ввод пользаком нового массива для разгадки
	{
		String Input[] = {" ", " ", "*", "*"};
		String numsInput; // Вводимая строка
		int condition = 4; // Стандартный размер массива с символами

		Scanner in = new Scanner(System.in);
		numsInput = in.next().toUpperCase(); // Строка с числами, которая в последствии должна быть разъединена

		if (itsThatWord(numsInput, "vis")) // Проверка на ввод чита
		{
			if (programm.cheatVis == true) 
			{
				Input[0] = "F";
				Input[1] = "U";		// Таков
				Input[2] = "C";		// Вывод
				Input[3] = "K";
			} else
			{
				programm.cheatVis = true;
				Input[0] = " ";
				Input[1] = "V";
				Input[2] = "I";
				Input[3] = " ";
			};
				
		}
		// |						|
		// |						|
		// V Основная работа метода V

		else { // Если чит не найден в введенной строке, то продолжаем игру как играли

			switch (numsInput.length()) // Проверка чтобы избежать exception
			{							// Избегаю его, потому что просить пользака вводить именно 4 символа
				case 3: condition = 3;  // не позволит использовать читы без больших костылей
				break;

				case 2: condition = 2;
				break;

				case 1: condition = 1; 
			}	

			for (int i = 0; i < condition; i++) // Заполнение массива числами. 
				Input[i] = numsInput.substring(i, i + 1); // i-й элемент массива есть строка-символ input-а
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