using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SalaryCalc
{
    class Program
    {
        static void Main(string[] args)
        {
            ISalaryCalculator salaryCalculator = new SalaryCalculator();
            Console.WriteLine("welcome to salary calculator !");
            Console.WriteLine("insert first name and then last name for getting salary details");
            string name = Console.ReadLine();
            string last = Console.ReadLine();
            Console.WriteLine("your salary is :{0}", salaryCalculator.GetSalary(name, last));
            Console.WriteLine("your worked {0} hours", salaryCalculator.GetWorkerHour(name, last));
            Console.WriteLine("thanks!!");
            Console.ReadLine();
            

        }
    }
}
