using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace SalaryCalc
{
    public struct WorkerData
    {
        string firstName;
        string lastName;

        public string First
        {
            get { return firstName; }
            set { firstName = value; }
        }

        public string Last
        {
            get { return lastName; }
            set { lastName = value; }
        }


    }


    class WorkerReader
    {
        const string SALARY_DATA = "SalaryData.xml";

         XmlDocument m_doc;
        Dictionary<WorkerData, int> m_workers = new Dictionary<WorkerData, int>();

        public Dictionary<WorkerData, int> Workers
        {
            get { return m_workers; }
        }

        public WorkerReader()
        {
            m_doc = new XmlDocument();
            ReadData();
        }



        public Dictionary<WorkerData,int> ReadData()
        {
                m_doc.Load(SALARY_DATA);
                
                XmlNode Workers =  m_doc["Workers"];
                foreach (XmlNode worker in Workers.ChildNodes)
                {
                    WorkerData tempdata = new WorkerData();
                    tempdata.First = worker.Attributes["FirstName"].Value.ToString();
                    tempdata.Last = worker.Attributes["LastName"].Value.ToString();
                    m_workers.Add(tempdata,int.Parse(worker.InnerText));
                }

                return m_workers;          
        }


    }
}
