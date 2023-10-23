# -*- coding: utf-8 -*-

import matplotlib.patches
import matplotlib.path
import matplotlib.pyplot as plt
from matplotlib.lines import Line2D
import numpy as np
from functions import *


def drawLine(axes):
    """
    Рисование линии
    """
    x0 = 0
    y0 = 0

    x1 = (-sqrt(4745) - 23)/62
    y1 = 1

    x2 = (sqrt(4745) - 23)/62
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

    r_1 = f_7(p_1)
    r_2 = f_7(p_2)
    r_3 = f_7(p_3)
    r_4 = f_7(p_4)

    polygon_2 = matplotlib.patches.Polygon([(r_1[0][0], r_1[1][0]),
                                            (r_2[0][0], r_2[1][0]),
                                            (r_3[0][0], r_3[1][0]),
                                            (r_4[0][0], r_4[1][0])], color='m')
    axes.add_patch(polygon_2)

   # plt.text(0.6, -0.7, "Исходный", horizontalalignment="center")




if __name__ == "__main__":
    plt.xlim(-2, 2)
    plt.ylim(-3, 3)
    plt.grid()

    # Получим текущие оси
    axes = plt.gca()
    axes.set_aspect("equal")

    drawLine(axes)
    drawPolygons(axes)
    #a = np.array([[5], [2]])
    #print(f_1(a)[0][0])
    plt.show()