from PIL import Image
import matplotlib.image as mpimg
import numpy as np


img = Image.open('red_eye.png')
black_and_white = img.convert('L')
black_and_white.save('bw_red.png')
rgb_image = mpimg.imread('bw_red.png')
# здесь получаем массив
U, s, W = np.linalg.svd(rgb_image)
V = W.T
D = np.zeros_like(rgb_image, dtype=float)
D[np.diag_indices(min(rgb_image.shape))] = s
np.dot(U.T, U)  # ортогональны
np.dot(V.T, V)  # ортогональны
# print(np.dot(np.dot(U, D), V.T))
# тут че-то все желто-синее(((
mpimg.imsave('after_svd.png', np.dot(np.dot(U, D), V.T))

# print(rgb_image[:200, :200])
