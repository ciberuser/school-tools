using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SalaryCalc
{
    interface ISalaryCalculator
    {
        int GetWorkerHour(string firstName, string lastName);
        int GetSalary(string firstName, string lastName);
    }
}
