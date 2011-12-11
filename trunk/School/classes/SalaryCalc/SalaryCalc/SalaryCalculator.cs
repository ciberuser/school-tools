using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SalaryCalc
{
    class SalaryCalculator:ISalaryCalculator
    {
        const int CELLING_1 = 100;
        const int CELLIN_2 = 130;
        const int CELLIN_3 = 160;
        const int CELLIN_4 = 180;


        #region ISalaryCalculator Members



        public float WorkerSalary(string firstName, string last)
        {
            throw new NotImplementedException();
        }

        public float[] GetAllSalaries()
        {
            throw new NotImplementedException();
        }

        #endregion
    }
}
