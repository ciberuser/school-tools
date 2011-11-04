using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDataDemo
{
    public class Group
    {
        string m_name;
        int m_id;
        List<Artist> m_Artists =  new List<Artist>();
        

        public Group() { }
           
        public Group(string name)
        {
            Name = name;
           
        }

        public List<Artist> Artists
        {
            get { return m_Artists; }
            set { m_Artists = value; }
        }

        public string Name
        {
            get { return m_name; }
            set { m_name = value; }
        }
        
        public int GroupID
        {
            get { return m_id; }
            set { m_id = value; }
        }

        

    }
}
