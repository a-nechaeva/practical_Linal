import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import numpy as np

rgb_image = mpimg.imread('turt.png')

weights = [0.2989, 0.5870, 0.1140]
gray_image = np.dot(rgb_image[..., :3], weights)
mpimg.imsave('wb_turt.png', gray_image)

figure, (left, right) = plt.subplots(1, 2, figsize=(12, 6))
left.imshow(rgb_image)
left.set_title('Original RGB Image')

right.imshow(gray_image, cmap='gray')  # вот cmap тут и работает)
right.set_title('Grayscale Image')

plt.show()