import matplotlib.patches
import matplotlib.path
import matplotlib.pyplot as plt
from matplotlib.lines import Line2D
import numpy as np
from scipy.linalg import expm


v_1 = [[1], [4]]
v_2 = [[2], [3]]

a_1 = [[-1.00, 0.00], [0.00, -1.00]]

a_2 = [[4, 1], [0, 4]]

a_3 = [[0, -1], [-16, 0]]

a_4 = [[1, -1], [5, -3]]

a_5 = [[3, -1], [5, -1]]

a_6 = [[2, -1], [5, -2]]

def m_t(m, time):
    new_m = [[4, 1], [0, 4]]
    for i in range(2):
        for j in range(2):
            new_m[i][j] = time * m[i][j]
    return new_m


#t = np.arange(-10, 10, 1)
#t = 3
t = []
cur_time = 0.000
x_1_1 = []
x_2_1 = []
x_1_2 = []
x_2_2 = []
x_1_3 = []
x_2_3 = []
x_01 = [[1], [4]]
x_02 = [[-2], [7]]
x_03 = [[9], [3]]

for i in range(1000):
    t.append(cur_time)
    x_1_1.append(np.matmul(expm(m_t(a_6, cur_time)), x_01)[0])
    x_2_1.append(np.matmul(expm(m_t(a_6, cur_time)), x_01)[1])

    x_1_2.append(np.matmul(expm(m_t(a_6, cur_time)), x_02)[0])
    x_2_2.append(np.matmul(expm(m_t(a_6, cur_time)), x_02)[1])

    x_1_3.append(np.matmul(expm(m_t(a_6, cur_time)), x_03)[0])
    x_2_3.append(np.matmul(expm(m_t(a_6, cur_time)), x_03)[1])
    cur_time += 0.01


plt.axes().set_aspect(1)

#plt.plot(t, x_1_1, label='1:x_1')
#plt.plot(t, x_2_1, label='1:x_2')
plt.plot(x_1_1, x_2_1, label='1:x_2 (x_1)')

#plt.plot(t, x_1_2, label='2:x_1')
#plt.plot(t, x_2_2, label='2:x_2')
plt.plot(x_1_2, x_2_2, label='2:x_2 (x_1)')

#plt.plot(t, x_1_3, label='3:x_1')
#plt.plot(t, x_2_3, label='3:x_2')
plt.plot(x_1_3, x_2_3, label='3:x_2 (x_1)')

#axes = plt.gca()
#ax2 = plt.axes([0, 0.6, 1, 1])

plt.grid()
plt.legend(bbox_to_anchor=(0.9, 1.0), loc='upper left')
plt.show()



