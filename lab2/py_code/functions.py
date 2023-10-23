import numpy as np
from math import *

m_1 = np.array([[-0.6, 0.8], [0.8, 0.6]])

m_2 = np.array([[1, 0], [8, 0]])

m_3 = np.array([[cos(5*pi/18), -sin(5*pi/18)], [sin(5*pi/18), cos(5*pi/18)]])

m_4 = np.array([[-1, 0], [0, -1]])

m_5 = np.array([[-0.3*sqrt(3) + 0.4, 0.4*sqrt(3) + 0.3], [0.4*sqrt(3) + 0.3, 0.3*sqrt(3) - 0.4]])

m_6 = np.array([[1, 1/8], [2, 1]])

m_7 = np.array([[4/3, -1/6], [-8/3, 4/3]])

m_8 = np.array([[1, 0], [10, -1]])

m_9 = np.array([[sqrt(5), 0], [0, sqrt(5)]])

m_10 = np.array([[3, 0], [0, 1]])

m_11 = np.array([[6/5, 2/5], [2/5, 9/5]])

m_12 = np.array([[4, 1], [0, 4]])

m_13 = np.array([[0, -1], [4, 0]])

m_14 = np.array([[0, 0], [0, 0]])

m_15_a = np.array([[1, 2], [3, 4]])

m_15_b = np.array([[5, 6], [7, 8]])

m_16_a = np.array([[1, 4], [0, 2]])

m_16_b = np.array([[9, 8], [0, 11]])


# НАДО ФИКСИТЬ, ПОТОМУ ЧТО ЭТО ПОВОРОТ
def f_1(vect):
    total = m_1.dot(vect)
    return total

def f_2(vect):
    total = m_2.dot(vect)
    return total

def f_3(vect):
    total = m_3.dot(vect)
    return total

def f_4(vect):
    total = m_4.dot(vect)
    return total

def f_5(vect):
    total = m_5.dot(vect)
    return total

def f_6(vect):
    total = m_6.dot(vect)
    return total

def f_7(vect):
    total = m_7.dot(vect)
    return total

def f_8(vect):
    total = m_8.dot(vect)
    return total

def f_9(vect):
    total = m_9.dot(vect)
    return total

def f_10(vect):
    total = m_10.dot(vect)
    return total

def f_11(vect):
    total = m_11.dot(vect)
    return total

def f_12(vect):
    total = m_12.dot(vect)
    return total

def f_13(vect):
    total = m_13.dot(vect)
    return total

def f_14(vect):
    total = m_14.dot(vect)
    return total

def f_15_a(vect):
    total = m_15_a.dot(vect)
    return total

def f_15_b(vect):
    total = m_15_b.dot(vect)
    return total

def f_15_ab(vect):
    total = m_15_a.dot(m_15_b.dot(vect))
    return total

def f_15_ba(vect):
    total = m_15_b.dot(m_15_a.dot(vect))
    return total

def f_16_a(vect):
    total = m_16_a.dot(vect)
    return total

def f_16_b(vect):
    total = m_16_b.dot(vect)
    return total

def f_16_ab(vect):
    total = m_16_a.dot(m_16_b.dot(vect))
    return total

def f_16_ba(vect):
    total = m_16_b.dot(m_16_a.dot(vect))
    return total


