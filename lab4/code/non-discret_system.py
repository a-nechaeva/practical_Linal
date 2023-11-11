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
    new_m = [[-1.00, 0.00], [0.00, -1.00]]
    for i in range(2):
        for j in range(2):
            new_m[i][j] = time * m[i][j]
    print(new_m)
    return new_m


#t = np.arange(-10, 10, 1)
#t = 3
t = []
cur_time = 0.00
x_1 = []
x_2 = []
x_01 = [[30], [80]]
x_02 = [[-2], [7]]
x_03 = [[9], [34]]

for i in range(10000):
    t.append(cur_time)
    x_1.append(np.matmul(expm(m_t(a_1, cur_time)), x_01)[0])
    x_2.append(np.matmul(expm(m_t(a_1, cur_time)), x_01)[1])
    cur_time += 0.01


#plt.plot(t, np.matmul(expm(m_t(a_1, t)), x_01)[0], t, np.matmul(expm(m_t(a_1, t)), x_02)[0], t, np.matmul(expm(m_t(a_1, t)), x_03)[0])
plt.plot(t, x_1, t, x_2, x_1, x_2)
axes = plt.gca()
axes.set_aspect("equal")

plt.show()


def drawLine(axes):
    """
    Рисование линии
    """
   # x1_t = lambda t: np.matmul(expm(matrix * t), start1[:2])[0]
    x0 = 0
    y0 = 0

    x1 = (-np.sqrt(4745) - 23)/62
    y1 = 1

    x2 = (np.sqrt(4745) - 23)/62
    y2 = 1.0

    line1 = Line2D([-30, 30], [0, 0], color="k")
    #axes.add_line(line1)

    line2 = Line2D([-28, 28], [-7, 7], color="k")
    #axes.add_line(line2)

    #plt.text(12, -1.5, "v1", horizontalalignment="center")
    #plt.text(23, 6.5, "v2", horizontalalignment="center")


def drawPolygons(axes):
    """
    Рисование многоугольника
    """
    p_1 = np.array([[-1], [0]])
    p_2 = np.array([[0], [-0.5]])
    p_3 = np.array([[1], [0]])
    p_4 = np.array([[0], [0.5]])
    polygon_1 = matplotlib.patches.Polygon([(p_1[0][0], p_1[1][0]),
                                            (p_2[0][0], p_2[1][0]),
                                            (p_3[0][0], p_3[1][0]),
                                            (p_4[0][0], p_4[1][0])], color='c')
    axes.add_patch(polygon_1)


"""plt.xlim(-2, 2)
plt.ylim(-3, 3)
plt.grid()

# Получим текущие оси

drawLine(axes)
drawPolygons(axes)
#a = np.array([[5], [2]])
#print(f_1(a)[0][0])
plt.show()"""

