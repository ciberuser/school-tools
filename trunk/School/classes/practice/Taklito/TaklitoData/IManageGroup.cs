using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoData
{
    public interface IManageGroup
    {
        void AddGroup(string name);
        List<Group> getAllGroups();

        
    }
}
