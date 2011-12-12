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
            Console.WriteLine("welcome to salary calculator !");
            //WorkerReader wr = new WorkerReader();
            ISalaryCalculator i_s = new SalaryCalculator();


        }
    }
}
