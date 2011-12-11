using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SalaryCalc
{
    interface ISalaryCalculator
    {
        float WorkerSalary(string firstName, string last);
        float[] GetAllSalaries();

    }
}
