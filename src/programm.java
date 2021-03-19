import java.util.* ;
// CHEAT vis -- позволяет видеть загаданный массив чисел
// CHEAT sv -- будет позволять увидеть числа на своих местах
// 
// TO-DO LIST
// 
// 1. При ходе игрока, выводится справа случайная надписьи разбивается построччно на три строки
// 
// 2. Проверить, если в INPUT два одинаковых числа ( бык - 1 )!!!!
//  А еще это должно быть связано с коровами


class programm
{
	static boolean arrsNotFullCompared = true; // Массивы полностью не сопадают
	static boolean cowExists = false; // Коровы не существуют
	static boolean bullExists = false; // быки не существуют
	static String Input;
	static Scanner in = new Scanner(System.in);
	static Msg msg = new Msg();
	static int stepsCounter;

	// ======Cheats======
	static boolean cheatVisIsInserted; // Чит отладки
	// static boolean cheatSv;  // Чит на упрощение прохождения, цифры на своих местах
	// ======Cheats======

	static int[] bullscows = {0, 0};
	static String[] secretMass;    // Генерация секретных чисел
	static String[] secretSymbs;   // Генерация случайных букв англ. алфавита


	public static void main(String[] args) {
		isThereCheatIn(args, "visChea");

		welcomes();
		Input = in.next();

		genArrs();
		StartCicle(); // Ожидает от пользака символа s или "2"
		TutorialCicle(); // Ожидает "t" или "1"

		msg.println("Желаем удачи!"); //Если введено "e" или "3"
	};


	static void welcomes()
	{
		msg.println("============================================");
		msg.println("Добро пожаловать в игру \"Быки и Коровы\"!");
		msg.println("============================================");
		msg.println("Было загадано 4-х значное число, сможете ли вы его угадать?");
		msg.println("");
		msg.print("Что дальше?  (Tutorial--t / 1) (Start--s / 2) (End--e / 3)  ");
	};

	public static boolean get(boolean cheat) 
	{
		return cheat;
	};

	public static boolean changeTo(boolean cheat, boolean arg)
	{
		return cheat = arg;
	};


	static void isThereCheatIn(String[] args, String cheat)
	{
		if (args.length == 1 && args[0].equals(cheat))
		{
			cheatVisIsInserted = true; // Читы, если включен, отображает отгаданные числа на своей позиции
			msg.println("cheat activated");
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

	public static void showArraysInGame(String[] input, String[] symbs, boolean cheatIns)
	{
		array.show(input, "?"); msg.println("");

		if (!cheatIns) msg.println(""); // Если чит не введен -- пустая строка

		array.show(symbs, "*"); msg.println("");

		if(cheatIns) {array.show(secretMass, "^"); msg.println("");}
	};

	public static void StartCicle() // Бесконечный цикл пока будет нажиматься кнопка s
	{	
		while( Input.equalsIgnoreCase("s") || Input.equals("2") )
		{
			if (arrsNotFullCompared == true)
			{
				stepsCounter++;
				msg.print("Введите число разгадку: (?) ");
				String[] inputPolz = array.input(); // Ввод массива для разгадки

				System.out.printf("=================--%d--======================", stepsCounter); msg.println("");
				array.compare(inputPolz, secretMass); // Сравнение элементов массива с загаданными

	 			showArraysInGame(inputPolz, secretSymbs, cheatVisIsInserted);

				msg.println("============================================");

				StartCicle();

				
			} 
			else gameEnd();			
		}
	};

	static void gameEnd()
	{
		msg.println("============================================");
		msg.print("Молодцы! Партия выйграна за "); msg.print(stepsCounter); msg.println(" шаг(а)(ов)!");

		arrsNotFullCompared = true; 
		genArrs();
		deleteBullsCows();
		stepsCounter = 0;
		cheatVisIsInserted = false;

		msg.println("============================================");
		msg.println("============================================");
		msg.print("Что дальше?  (Tutorial--t) (Start--s) (End--e)  ");
		Input = in.next();
	};

	public static void TutorialCicle()
	{
		String[] d = {" ","v","v"," "}; // Tutorial
		String[] a = {"1", "2", "3", "1"};
		String[] b = {"A","3","1","D"};    
		String[] c = {" ","^","^"," "};

		while(Input.equalsIgnoreCase("t") || Input.equals("1")) // Tutorial part, обучаеющее место
		{
			msg.println("=====================================================================");
			msg.println("Суть игры состоит в следующем: "); msg.println("");
			msg.println("Игрок вводит комбинации одну за другой, пока не отгадает всю последовательность.");
			msg.println("");
			array.show(d, "?", "tutor"); msg.println("");
			array.show(a, "?"); msg.print("Это числа, которые ты ввел"); msg.println("");
			array.show(b, "*"); msg.print(" Это загадка, то, где будут видны быки и коровы"); msg.println("");
			array.show(c, "*", "tutor"); msg.println("");
			msg.println("=====================================================================");

			msg.print("Что дальше?  (Tutorial--t) (Start--s) (End--e)  ");
			Input = in.next();
			if (Input != "t" || Input != "1") StartCicle();
		}
	};

}

class Msg
{
	public static void println(String message) // Вывод сообщения на экран консоли
	{
		System.out.println(message);
	};

	public static void print(String message) // Вывод сообщения на экран консоли (!) без новой строки
	{
		System.out.print(message);
	};

	public static void println(int message) // Вывод сообщения на экран консоли
	{
		System.out.println(message);
	};

	// int, числа
	public static void print(int message) // Вывод сообщения на экран консоли (!) без новой строки
	{
		System.out.print(message);
	};
};

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
					bullsIters[i] = true; programm.bullscows[0] += 1;  // быки
				}
			
			}	
		}


		if (programm.bullscows[0] >= 4) programm.bullscows[0] = Math.round(programm.bullscows[0] / 2 + 2);
		// если коров больше 3-х то быки -3
		// Если два числа в Input одинаковы то быки -1
		if (programm.bullscows[1] > 0) programm.cowExists = true; programm.bullscows[0] -= programm.bullscows[1]; // коровы существуют
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

	public static void show(String[] array, String ask) // Показывает массив из 4-х символов, БЕЗ ПЕРЕНОСА СТРОКИ
	{													
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
	{
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
		String viewInput[] = {"*", "*", "*", "*"};
		String numsInput; // Вводимая строка
		int condition = 4; // Стандартный размер массива с символами

		Scanner in = new Scanner(System.in);
		numsInput = in.next().toUpperCase(); // Строка с числами, которая в последствии должна быть разъединена

		checkCheat("vis", numsInput);

		for (int i = 0; i < numsInput.length(); i++) // Заполнение массива числами. 
			viewInput[i] = numsInput.substring(i, i + 1); // i-й элемент массива есть строка-символ input-а

		return viewInput;

	};

	static void checkCheat(String cheat, String input)
	{
		if ( itsThatWord(cheat, input) ) programm.cheatVisIsInserted = true;
	};

	public static String[] randomSymbStringArr() // Создает массив из различных цифр-символов по таблице ASCII (рандомно)
	{
		String[] symbols = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String[] newArray = new String[4];

		for (int i = 0; i < 4; i++)
			newArray[i] = symbols[(int) (Math.random() * 26)]; // 26 -- symbols.length

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