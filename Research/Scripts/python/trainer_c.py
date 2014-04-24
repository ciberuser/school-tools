


import numpy as np
import pandas as pd
import random
import utils
from base_c import base_c
import csv
from sklearn.metrics import mean_squared_error,r2_score
from sklearn.ensemble import GradientBoostingRegressor
 

class trainer_c(base_c):
    
    
    def InitcolumnNames(self,trainTableFile):
        columnNames =[]
        ifile  = open(trainTableFile, "rb")
        reader = csv.reader(ifile)
        rowNum = 0
        for row in reader:
            if rowNum > 0 :
                break
            for col in row :
                columnNames.append(col)
            rowNum=+1
        
        self.LogPrint("success import table headers num of column =%d" % len(columnNames))
        return columnNames
    
    def __init__(self,trainTableFile):
        if not utils.PathExist(trainTableFile):
            self.LogPrint("missing machine learning table file will exist...")
            return -1
        self.m_tainFile = trainTableFile
        
        self.m_columnNames=self.InitcolumnNames(self.m_tainFile)
        self.m_machinePandasMatrix = pd.read_csv(trainTableFile,skiprows=1, sep=',',names=self.m_columnNames)
        
    def StartTraining(self):
        X = self.m_machinePandasMatrix[self.m_machinePandasMatrix.columns - ['user']]
        Y = self.m_machinePandasMatrix['user']
        rows = random.sample(self.m_machinePandasMatrix.index, int(len(self.m_machinePandasMatrix)*.80))
        x_train, y_train = X.ix[rows],Y.ix[rows]
        x_test,y_test  = X.drop(rows),Y.drop(rows)
        
        params = {'n_estimators': 500, 'max_depth': 6,'learn_rate': 0.1, 'loss': 'huber','alpha':0.95}
        clf = GradientBoostingRegressor(**params).fit(x_train, y_train)
        
        

###########################
#   main
###########################        