using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Linq;

namespace TaklitoDBInterface
{
    public class TableMaster:IGroupManager
    {
        TaklitoContext m_context;
        DataContext m_db;
       public TableMaster()
       {
           m_context = TaklitoContext.GetIntance();
           m_db = m_context.TaklitoDataContext1;
       }


        public Album[] GetAllAlbum()
        {
            if (!m_db.DatabaseExists()) return null;
            Table<Album> albums = m_db.GetTable<Album>();
            return  albums.ToArray();
        }

    
#region IGroupManager Members

public void  AddGroup(string name)
{
 	throw new NotImplementedException();
}

public Group[]  GetAllGroups()
{
    if (!m_db.DatabaseExists()) return null;
    Table<Group> groups = m_db.GetTable<Group>();
    return groups.ToArray();
}

public void  RemoveGroup(int groupId)
{
 	throw new NotImplementedException();
}

public void  RemoveGroup(string GroupName)
{
 	throw new NotImplementedException();
}

public int  GetGroupId(string GroupName)
{
 	throw new NotImplementedException();
}

public void  JoinArtistToGroup(Artist artist, ref Group group)
{
 	throw new NotImplementedException();
}

public void  JoinArtistToGroup(Artist artist, int groupId)
{
 	throw new NotImplementedException();
}

public void  MoveArtistFromGroupToGroup(Artist artist, int groupFrom, int groupTo)
{
 	throw new NotImplementedException();
}

public void  RemoveArtistFromGroup(Artist artistId, int groupId)
{
 	throw new NotImplementedException();
}

public void  AddAlbum(Album album, int groupId)
{
 	throw new NotImplementedException();
}

public Group  FindGroup(string name)
{
 	throw new NotImplementedException();
}

#endregion
}
}
