﻿#pragma warning disable 1591
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.225
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace TaklitoData
{
	using System.Data.Linq;
	using System.Data.Linq.Mapping;
	using System.Data;
	using System.Collections.Generic;
	using System.Reflection;
	using System.Linq;
	using System.Linq.Expressions;
	using System.ComponentModel;
	using System;
	
	
	[global::System.Data.Linq.Mapping.DatabaseAttribute(Name="Taklito")]
	public partial class TaklitoLinqDataContext : System.Data.Linq.DataContext
	{
		
		private static System.Data.Linq.Mapping.MappingSource mappingSource = new AttributeMappingSource();
		
    #region Extensibility Method Definitions
    partial void OnCreated();
    partial void InsertArtist(Artist instance);
    partial void UpdateArtist(Artist instance);
    partial void DeleteArtist(Artist instance);
    partial void InsertGroup(Group instance);
    partial void UpdateGroup(Group instance);
    partial void DeleteGroup(Group instance);
    #endregion
		
		public TaklitoLinqDataContext() : 
				base(global::TaklitoData.Properties.Settings.Default.TaklitoConnectionString, mappingSource)
		{
			OnCreated();
		}
		
		public TaklitoLinqDataContext(string connection) : 
				base(connection, mappingSource)
		{
			OnCreated();
		}
		
		public TaklitoLinqDataContext(System.Data.IDbConnection connection) : 
				base(connection, mappingSource)
		{
			OnCreated();
		}
		
		public TaklitoLinqDataContext(string connection, System.Data.Linq.Mapping.MappingSource mappingSource) : 
				base(connection, mappingSource)
		{
			OnCreated();
		}
		
		public TaklitoLinqDataContext(System.Data.IDbConnection connection, System.Data.Linq.Mapping.MappingSource mappingSource) : 
				base(connection, mappingSource)
		{
			OnCreated();
		}
		
		public System.Data.Linq.Table<Artist> Artists
		{
			get
			{
				return this.GetTable<Artist>();
			}
		}
		
		public System.Data.Linq.Table<Group> Groups
		{
			get
			{
				return this.GetTable<Group>();
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.TableAttribute(Name="dbo.Artist")]
	public partial class Artist : INotifyPropertyChanging, INotifyPropertyChanged
	{
		
		private static PropertyChangingEventArgs emptyChangingEventArgs = new PropertyChangingEventArgs(String.Empty);
		
		private int _ArtistID;
		
		private string _First_Name;
		
		private string _Last_Name;
		
		private string _Position;
		
		private System.Nullable<int> _FK_GroupID;
		
		private EntityRef<Group> _Group;
		
    #region Extensibility Method Definitions
    partial void OnLoaded();
    partial void OnValidate(System.Data.Linq.ChangeAction action);
    partial void OnCreated();
    partial void OnArtistIDChanging(int value);
    partial void OnArtistIDChanged();
    partial void OnFirst_NameChanging(string value);
    partial void OnFirst_NameChanged();
    partial void OnLast_NameChanging(string value);
    partial void OnLast_NameChanged();
    partial void OnPositionChanging(string value);
    partial void OnPositionChanged();
    partial void OnFK_GroupIDChanging(System.Nullable<int> value);
    partial void OnFK_GroupIDChanged();
    #endregion
		
		public Artist()
		{
			this._Group = default(EntityRef<Group>);
			OnCreated();
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_ArtistID", AutoSync=AutoSync.OnInsert, DbType="Int NOT NULL IDENTITY", IsPrimaryKey=true, IsDbGenerated=true)]
		public int ArtistID
		{
			get
			{
				return this._ArtistID;
			}
			set
			{
				if ((this._ArtistID != value))
				{
					this.OnArtistIDChanging(value);
					this.SendPropertyChanging();
					this._ArtistID = value;
					this.SendPropertyChanged("ArtistID");
					this.OnArtistIDChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Name="[First Name]", Storage="_First_Name", DbType="NChar(10)")]
		public string First_Name
		{
			get
			{
				return this._First_Name;
			}
			set
			{
				if ((this._First_Name != value))
				{
					this.OnFirst_NameChanging(value);
					this.SendPropertyChanging();
					this._First_Name = value;
					this.SendPropertyChanged("First_Name");
					this.OnFirst_NameChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Name="[Last Name]", Storage="_Last_Name", DbType="NChar(10)")]
		public string Last_Name
		{
			get
			{
				return this._Last_Name;
			}
			set
			{
				if ((this._Last_Name != value))
				{
					this.OnLast_NameChanging(value);
					this.SendPropertyChanging();
					this._Last_Name = value;
					this.SendPropertyChanged("Last_Name");
					this.OnLast_NameChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_Position", DbType="Text", UpdateCheck=UpdateCheck.Never)]
		public string Position
		{
			get
			{
				return this._Position;
			}
			set
			{
				if ((this._Position != value))
				{
					this.OnPositionChanging(value);
					this.SendPropertyChanging();
					this._Position = value;
					this.SendPropertyChanged("Position");
					this.OnPositionChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_FK_GroupID", DbType="Int")]
		public System.Nullable<int> FK_GroupID
		{
			get
			{
				return this._FK_GroupID;
			}
			set
			{
				if ((this._FK_GroupID != value))
				{
					if (this._Group.HasLoadedOrAssignedValue)
					{
						throw new System.Data.Linq.ForeignKeyReferenceAlreadyHasValueException();
					}
					this.OnFK_GroupIDChanging(value);
					this.SendPropertyChanging();
					this._FK_GroupID = value;
					this.SendPropertyChanged("FK_GroupID");
					this.OnFK_GroupIDChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.AssociationAttribute(Name="Group_Artist", Storage="_Group", ThisKey="FK_GroupID", OtherKey="GroupID", IsForeignKey=true)]
		public Group Group
		{
			get
			{
				return this._Group.Entity;
			}
			set
			{
				Group previousValue = this._Group.Entity;
				if (((previousValue != value) 
							|| (this._Group.HasLoadedOrAssignedValue == false)))
				{
					this.SendPropertyChanging();
					if ((previousValue != null))
					{
						this._Group.Entity = null;
						previousValue.Artists.Remove(this);
					}
					this._Group.Entity = value;
					if ((value != null))
					{
						value.Artists.Add(this);
						this._FK_GroupID = value.GroupID;
					}
					else
					{
						this._FK_GroupID = default(Nullable<int>);
					}
					this.SendPropertyChanged("Group");
				}
			}
		}
		
		public event PropertyChangingEventHandler PropertyChanging;
		
		public event PropertyChangedEventHandler PropertyChanged;
		
		protected virtual void SendPropertyChanging()
		{
			if ((this.PropertyChanging != null))
			{
				this.PropertyChanging(this, emptyChangingEventArgs);
			}
		}
		
		protected virtual void SendPropertyChanged(String propertyName)
		{
			if ((this.PropertyChanged != null))
			{
				this.PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.TableAttribute(Name="dbo.[Group]")]
	public partial class Group : INotifyPropertyChanging, INotifyPropertyChanged
	{
		
		private static PropertyChangingEventArgs emptyChangingEventArgs = new PropertyChangingEventArgs(String.Empty);
		
		private int _GroupID;
		
		private string _Name;
		
		private EntitySet<Artist> _Artists;
		
    #region Extensibility Method Definitions
    partial void OnLoaded();
    partial void OnValidate(System.Data.Linq.ChangeAction action);
    partial void OnCreated();
    partial void OnGroupIDChanging(int value);
    partial void OnGroupIDChanged();
    partial void OnNameChanging(string value);
    partial void OnNameChanged();
    #endregion
		
		public Group()
		{
			this._Artists = new EntitySet<Artist>(new Action<Artist>(this.attach_Artists), new Action<Artist>(this.detach_Artists));
			OnCreated();
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_GroupID", AutoSync=AutoSync.OnInsert, DbType="Int NOT NULL IDENTITY", IsPrimaryKey=true, IsDbGenerated=true)]
		public int GroupID
		{
			get
			{
				return this._GroupID;
			}
			set
			{
				if ((this._GroupID != value))
				{
					this.OnGroupIDChanging(value);
					this.SendPropertyChanging();
					this._GroupID = value;
					this.SendPropertyChanged("GroupID");
					this.OnGroupIDChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_Name", DbType="NChar(10)")]
		public string Name
		{
			get
			{
				return this._Name;
			}
			set
			{
				if ((this._Name != value))
				{
					this.OnNameChanging(value);
					this.SendPropertyChanging();
					this._Name = value;
					this.SendPropertyChanged("Name");
					this.OnNameChanged();
				}
			}
		}
		
		[global::System.Data.Linq.Mapping.AssociationAttribute(Name="Group_Artist", Storage="_Artists", ThisKey="GroupID", OtherKey="FK_GroupID")]
		public EntitySet<Artist> Artists
		{
			get
			{
				return this._Artists;
			}
			set
			{
				this._Artists.Assign(value);
			}
		}
		
		public event PropertyChangingEventHandler PropertyChanging;
		
		public event PropertyChangedEventHandler PropertyChanged;
		
		protected virtual void SendPropertyChanging()
		{
			if ((this.PropertyChanging != null))
			{
				this.PropertyChanging(this, emptyChangingEventArgs);
			}
		}
		
		protected virtual void SendPropertyChanged(String propertyName)
		{
			if ((this.PropertyChanged != null))
			{
				this.PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
			}
		}
		
		private void attach_Artists(Artist entity)
		{
			this.SendPropertyChanging();
			entity.Group = this;
		}
		
		private void detach_Artists(Artist entity)
		{
			this.SendPropertyChanging();
			entity.Group = null;
		}
	}
}
#pragma warning restore 1591