import copy

import matplotlib.pyplot as plt
import numpy as np


v_1 = [[1], [4]]
v_2 = [[2], [3]]

a_1 = [[-1, 1], [0, -1]]

a_2 = [[-1/(np.sqrt(2)), -1/2], [1, -1/(np.sqrt(2))]]

a_3 = [[1, -2], [1, -1]]

a_4 = [[1/(np.sqrt(2)), 1/2], [-1, 1/(np.sqrt(2))]]

a_5 = [[1/2, -1/4], [1, 3/2]]

a_6 = [[-1/2, 1], [0, -1/2]]

a_7 = [[1, -5/4], [1, -1]]

a_8 = [[1/4, -1/16], [1, 3/4]]

a_9 = [[-2, 1], [0, -2]]

a_10 = [[1, -5], [1, -1]]

a_11 = [[2, 1], [0, 2]]

a_12 = [[0, 1], [0, 0]]


def m_k(m, k):
    new_m = copy.deepcopy(m)
    matrix = copy.deepcopy(m)
    if k > 1:
        for i in range(k - 1):
            new_m = np.matmul(new_m, matrix)

    return new_m


x_01 = [[1], [4]]
x_02 = [[-2], [7]]
x_03 = [[9], [3]]

x_1_1 = []
x_2_1 = []
x_1_2 = []
x_2_2 = []
x_1_3 = []
x_2_3 = []

t = []
cur_time = 0.0

for i in range(10):
    cur_m = m_k(a_12, i)
    t.append(i)
    x_1_1.append(np.matmul(cur_m, x_01)[0])
    x_2_1.append(np.matmul(cur_m, x_01)[1])

    x_1_2.append(np.matmul(cur_m, x_02)[0])
    x_2_2.append(np.matmul(cur_m, x_02)[1])

    x_1_3.append(np.matmul(cur_m, x_03)[0])
    x_2_3.append(np.matmul(cur_m, x_03)[1])


plt.axes().set_aspect(0.8)

plt.plot(t, x_1_1, label='1:x_1')
plt.plot(t, x_2_1, label='1:x_2')
#plt.plot(x_1_1, x_2_1, label='1:x_2 (x_1)')

plt.plot(t, x_1_2, label='2:x_1')
plt.plot(t, x_2_2, label='2:x_2')
#plt.plot(x_1_2, x_2_2, label='2:x_2 (x_1)')

plt.plot(t, x_1_3, label='3:x_1')
plt.plot(t, x_2_3, label='3:x_2')
#plt.plot(x_1_3, x_2_3, label='3:x_2 (x_1)')

#axes = plt.gca()
#ax2 = plt.axes([0, 0.6, 1, 1])

plt.grid()
plt.legend(bbox_to_anchor=(0.7, 1.0), loc='upper left')
plt.show()


