#Импортируем библиотеку scipy: метод hierarchy для кластеризации и метод pdist для расчёта попарных расстояний
import numpy as np
import matplotlib.pyplot as plt
from scipy.cluster import hierarchy
from scipy.spatial.distance import pdist
#Случайно в интервалах зададим данные для кластеризации
X = np.zeros((150, 2))
np.random.seed(seed=42)
X[:50, 0] = np.random.normal(loc=0.0, scale=.3, size=50)
X[:50, 1] = np.random.normal(loc=0.0, scale=.3, size=50)
X[50:100, 0] = np.random.normal(loc=2.0, scale=.5, size=50)
X[50:100, 1] = np.random.normal(loc=-1.0, scale=.2, size=50)
X[100:150, 0] = np.random.normal(loc=-1.0, scale=.2, size=50)
X[100:150, 1] = np.random.normal(loc=2.0, scale=.5, size=50)
distance_mat = pdist(X)
# pdist посчитает нам верхний треугольник матрицы попарных Евклидовых расстояний
Z = hierarchy.linkage(distance_mat, 'single')
#linkage — реализация агломеративного алгоритма (см. рис.2.9)
plt.figure(figsize=(10, 5))
dn = hierarchy.dendrogram(Z, color_threshold=0.5)
plt.show()
