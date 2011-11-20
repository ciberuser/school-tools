using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDataDemo
{
    public class Artist
    {
        string m_frist;
        string m_last;
        string m_pos;
        Group m_group;
        List<Album> m_albumes = new List<Album>();

        internal List<Album> Albumes
        {
            get { return m_albumes; }
        }
        
        public string FirstName
        {
            get { return m_frist; }
            set { m_frist = value; }
        }
        
        public string LastName
        {
            get { return m_last; }
            set { m_last = value; }
        }
       
        public string Position
        {
            get { return m_pos; }
            set { m_pos = value; }
        }
        
        internal Group Group
        {
            get { return m_group; }
            set { m_group = value; }
        }
    
    }
}
