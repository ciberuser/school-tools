using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.InteropServices;

namespace SalaryCalc
{
    class Program
    {
        [DllImport("msvcrt")]
        static extern int _getch();

        static void Main(string[] args)
        {
            ISalaryCalculator salaryCalculator = new SalaryCalculator();
            Console.WriteLine("welcome to salary calculator !");
            Console.WriteLine("insert first name and then last name for getting salary details");
            string name = Console.ReadLine();
            string last = Console.ReadLine();
            int sal = salaryCalculator.GetSalary(name, last);
            if (sal == 0) 
            {
                Console.WriteLine("worker didn't found!!");
            }
            else
            {
                Console.WriteLine("your salary is :{0}", sal);
                Console.WriteLine("your worked {0} hours", salaryCalculator.GetWorkerHour(name, last));
                Console.WriteLine("thanks!!");
            }
            Console.WriteLine("Press any key to end..");
            _getch(); // this works only when import dll 
        
           
            
            

        }
    }
}
