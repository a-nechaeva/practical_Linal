# -*- coding: utf-8 -*-

import matplotlib.patches
import matplotlib.path
import matplotlib.pyplot as plt
from matplotlib.lines import Line2D


def drawLine(axes):
    """
    Рисование линии
    """
    x0 = 0
    y0 = 0

    x1 = 1
    y1 = 0.5

    x2 = 0.5
    y2 = 1.0

    line = Line2D([x0, x1, x2], [y0, y1, y2], color="k")
    axes.add_line(line)

    plt.text(0.5, 1.1, "Line2D", horizontalalignment="center")


def drawPolygons(axes):
    """
    Рисование многоугольника
    """
    polygon_1 = matplotlib.patches.Polygon([(0, -0.75),
                                            (0, -1.25),
                                            (0.5, -1.25),
                                            (1, -0.75)])
    axes.add_patch(polygon_1)
    plt.text(0.6, -0.7, "Polygon", horizontalalignment="center")

    polygon_2 = matplotlib.patches.Polygon([(-0.5, 0),
                                            (-1, -0.5),
                                            (-1, -1),
                                            (-0.5, -1)],
                                           fill=False,
                                           closed=False)
    axes.add_patch(polygon_2)
    plt.text(-1.0, -0.1, "Polygon", horizontalalignment="center")




if __name__ == "__main__":
    plt.xlim(-2, 2)
    plt.ylim(-2, 2)
    plt.grid()

    # Получим текущие оси
    axes = plt.gca()
    axes.set_aspect("equal")

    drawLine(axes)
    drawPolygons(axes)

    plt.show()