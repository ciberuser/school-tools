using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SalaryCalc
{
    class SalaryCalculator:ISalaryCalculator
    {

        public delegate int salaryFanction(int hours);

        const int HOUR_PAY = 30; 
        enum ECellin
        {
            eLevel_1 = 100,
            eLevel_2 = 130,
            eLevel_3 = 160,
            eLevel_4 = 180
        }

        Dictionary<ECellin, salaryFanction> m_salariesFunction;
        WorkerReader m_workerDataBase;
        void Init()
        {
            m_salariesFunction = new Dictionary<ECellin, salaryFanction>();
            m_salariesFunction.Add(ECellin.eLevel_1, hours => (hours * HOUR_PAY));
            m_salariesFunction.Add(ECellin.eLevel_2, hours => (hours * HOUR_PAY) +400);
            m_salariesFunction.Add(ECellin.eLevel_3, hours => ((hours * HOUR_PAY) + 800));
            m_salariesFunction.Add(ECellin.eLevel_4, hours => ((hours * HOUR_PAY) +1000));
        }

        public SalaryCalculator()
        {
            Init();
            m_workerDataBase = new WorkerReader();
            int h = GetSalary("ori", "alon");
        }
                        
        
        #region ISalaryCalculator Members

        public int GetSalary(string firstName, string lastName)
        {
            int hours =  WorkerHour(firstName, lastName);
            foreach (var level in Enum.GetValues(typeof(ECellin)))
            {
                if (hours < (int)level)
                    return m_salariesFunction[(ECellin)level](hours);
            }
            return 0;
            
        }
        
        public int WorkerHour(string firstName, string last)
        {
            return  m_workerDataBase.Workers.Where(x => (x.Key.First == firstName && x.Key.Last == last)).FirstOrDefault().Value;
        }
                

        #endregion
    }
}
