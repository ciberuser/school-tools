using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoData
{
    public class ManageGroup: IManageGroup
    {
        #region IManageGroup Members
        TaklitoLinqDataContext m_database;
        public ManageGroup()
        {
             m_database = new TaklitoData.TaklitoLinqDataContext();

        }

        public List<Group> getAllGroups()
        {
          
            return m_database.Groups.ToList();
        }

        public void AddGroup(string name)
        {
            Group newGroup = new Group();
            newGroup.Name = name;
            m_database.SubmitChanges();
         }

        #endregion
    }
}
