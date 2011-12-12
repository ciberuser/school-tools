using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SalaryCalc
{
    interface ISalaryCalculator
    {
        int WorkerHour(string firstName, string last);
        float[] GetAllSalaries();

    }
}
