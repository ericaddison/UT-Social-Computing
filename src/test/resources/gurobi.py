import csv
from gurobipy import *
import numpy as np
np.set_printoptions(precision=3)
import math

A_reader = csv.reader(open("stam.csv", "rb"), delimiter=",")
A = np.array(list(A_reader)).astype("float")


m, n = A.shape
m, n = int(m), int(n)

print (n,m)
# print(A)
print(A[1,0])
m5 = Model()
x = []
for i in range(0,n):
    y = []
    for j in range(0,n):
        y.append(m5.addVar(vtype=GRB.BINARY))
    x.append(y)

for v in range(0,n):
    m5.addConstr(quicksum(x[v][i] for i in range(n)) <= 1)
    m5.addConstr(quicksum(x[i][v] for i in range(n)) <= 1)

expr = LinExpr()

for j in range(0,m):
    for i in range(0,n):
        expr += A[j,i]*x[j][i]

m5.setObjective(expr,GRB.MAXIMIZE)

m5.optimize()
print("Optimal Solution={}".format(m5.objVal))
h = {}
for i in range(n):
    for j in range(n):
        if x[j][i].X:
            h[(j+1,i+1)] = 1
for k in sorted(h.keys()):
    print(k)