using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Linq;

namespace TaklitoDBInterface
{
    class TaklitoDataContext:DataContext
    {
        private Table<Group> m_Groups;

        public Table<Group> Groups
        {
            get {
                m_Groups = this.GetTable<Group>();
                return m_Groups;
            }
            
        }

        private Table<Artist> m_Artists;

        public Table<Artist> Artists
        {
            get {
                m_Artists = this.GetTable<Artist>();
                return m_Artists; 
            }
           
        }
        private Table<Album> m_Albums;

        public Table<Album> Albums
        {
            get {
                m_Albums = this.GetTable<Album>();

                return m_Albums; 
            }
           
        }

        public TaklitoDataContext(string filePath):base(filePath)
        {
                         
        }
    }
}
