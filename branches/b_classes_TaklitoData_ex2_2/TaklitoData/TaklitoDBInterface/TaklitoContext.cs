using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Linq;

namespace TaklitoDBInterface
{
    public class TaklitoContext
    {
        string TAKLITO_MDF_PATH = "C:\\SchoolLocalSVN\\trunk\\School\\classes\\practice1\\Practice1\\TaklitoData\\TaklitoDBInterface\\TaklitoDataBase.mdf";


        public static TaklitoContext GetIntance()
        {
            if (m_inteance == null)
                m_inteance = new TaklitoContext();

            return m_inteance;

        }

        private TaklitoContext()
        {
            m_taklitoContext = new DataContext(TAKLITO_MDF_PATH);
        }

        static TaklitoContext m_inteance;
        DataContext m_taklitoContext;

        public DataContext TaklitoDataContext1
        {
            get { return m_taklitoContext; }
        }


    }
}
