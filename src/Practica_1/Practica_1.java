package Practica_1;

import java.util.Arrays;
import java.util.Scanner;

public class Practica_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		Scanner input_space = new Scanner(System.in).useDelimiter("\n");
		String nombre = "";
		String contraseña = "";
		int opcion = 0;
		boolean acceso = false;

		int tamaño = 10;

		String[] nombreProductos = new String[tamaño];
		String[] codigoDescuentos = new String[tamaño];
		double[] precioProductos = new double[tamaño];
		int[] vTotales = new int[tamaño];
		double[] porcentajesD = new double[tamaño];
		int contadorP = 0;
		int contadorD = 0;

		int contadorVentas = 0;

		while (!acceso) {
			System.out.println("INICIAR SESIÓN");
			System.out.print("🙍‍♂️ Ingrese su Usuario: ");
			nombre = input.nextLine();
			System.out.print("\n🔒 Ingrese su Contraseña: ");
			contraseña = input.nextLine();
			if (nombre.equals("c") && contraseña.equals("i")) {
				acceso = true;
			} else {
				System.out.println("\n❌ Datos incorrectos ❌\n");
			}
		}

		if (acceso) {
			System.out.println("Bienvenido");

			while (opcion != 5) {
				try {
					opciones();
					opcion = input.nextInt();
					switch (opcion) {
					case 1:
						boolean op1 = true;
						while (op1) {
							String nombreProducto = "";
							double precioProducto = 0;
							System.out.print("Ingrese el Nombre del producto: \n");
							nombreProducto = input_space.nextLine();
							System.out.print("Ingrese el Precio del producto: ");
							precioProducto = input.nextDouble();
							boolean existencia = verificarExistencia(nombreProducto, nombreProductos);
							if (existencia == true) {
								System.out.println("❌ Este producto ya ha sido ingresado anteriormente ❌");
							} else if (!(precioProducto >= 0)) {
								System.out.println("❌ Debe ingresar un Producto con costo mayor a 0 ❌");
							} else if (contadorP > (tamaño - 1)) {
								System.out.println("❌ La capacidad máxima de productos ha sido alcanzada ❌");
							} else {
								nombreProductos[contadorP] = nombreProducto;
								precioProductos[contadorP] = precioProducto;
								vTotales[contadorP] = 0;
								contadorP++;
								System.out.println("✅ Productos registrados Exitosamente ✅");
								op1 = false;
							}
						}
						break;
					case 2:
						boolean op2 = true;
						while (op2) {
							String codigoD = "";
							double porcentaje = 0.0;

							System.out.print("Ingrese el codigo del descuento: ");
							codigoD = input.next();
							System.out.print("Ingrese el porcentaje del descuento: ");
							porcentaje = input.nextDouble();

							boolean existenciaD = verificarExistencia(codigoD, codigoDescuentos);
							if (existenciaD == true) {
								System.out.println("❌ Este Cupon ya ha sido ingresado anteriormente ❌");
							} else if (!(codigoD.length() == 4)) {
								System.out.println("❌ Debe ingresar un codigo de 4 caracteres ❌");
							} else if (!(porcentaje > 0 && porcentaje < 100)) {
								System.out.println("❌ Debe ingresar un descuento entre 0 y 100 ❌");
							} else if (contadorP > (tamaño - 1)) {
								System.out.println("❌ La capacidad máxima de cupones ha sido alcanzada ❌");
							} else {
								codigoDescuentos[contadorD] = codigoD;
								porcentajesD[contadorD] = porcentaje;
								contadorD++;
								System.out.println("✅ Cupones registrados Exitosamente ✅");
								op2 = false;
							}
						}
						break;
					case 3:
						String nombreCliente = "";
						long Nit = 0;

						System.out.print("Ingrese el nombre del Cliente");
						nombreCliente = input_space.nextLine();
						System.out.println("Ingrese el Nit del usuario (Si no posee ingrese 0)");
						Nit = input.nextLong();
						int[] noProducto = new int[tamaño];
						int[] cantidadRVentas = new int[tamaño];

						if (!nombreCliente.equals("")) {
							imprimirProductos(nombreProductos, precioProductos);
							boolean pVentas = true;
							double total = 0;
							while (pVentas) {
								System.out.print("Ingrese el No del Producto (Ingrese 0 para culminar la compra): ");
								int auxiliar = input.nextInt();
								// auxiliar = ;

								if (auxiliar == 0) {
									break;
								} else if (auxiliar < 0 || auxiliar > contadorP) {
									System.out.println("Ingrese un No. correcto");
								} else {
									System.out.print("Ingrese la cantidad de" + nombreProductos[auxiliar - 1] + ": ");
									int cant = input.nextInt();
									cantidadRVentas[auxiliar - 1] += cant;
									vTotales[auxiliar - 1] = vTotales[auxiliar - 1] + cant;
									noProducto[contadorVentas] = auxiliar;
									// total += precioProductos[auxiliar - 1] * cant;
									contadorVentas++;
								}

							}
							System.out.print("Tiene algun codigo de descuento: ");
							String codigoIngresado = input.next();
							boolean existenciaDIngresado = verificarExistencia(codigoIngresado, codigoDescuentos);
							double porcentaje = 0;
							if (existenciaDIngresado) {
								int posicion = 0;
								for (int i = 0; i < codigoDescuentos.length; i++) {
									if (codigoDescuentos[i] == codigoIngresado) {
										posicion = i;
										break;
									}
								}
								// total = total - total * (porcentajesD[posicion] / 100);
								porcentaje = porcentajesD[posicion];
							} else {
								codigoIngresado = "";
							}
							imprimirFactura(nombreCliente, Nit, nombreProductos, precioProductos, cantidadRVentas,
									codigoIngresado, porcentaje);
							// imprimirFactura(nombreCliente, Nit, nombreProductos, noProducto,
							// precioProductos, total);

						}
						break;
					case 4:
						System.out.println(Arrays.toString(vTotales));
						int[] pos = burbuja(vTotales);
						//System.out.println(vTotales[pos.length-1]);
						for (int i = 0; i < pos.length; i++) {
							if (nombreProductos[i] != null ) {
								// System.out.println(ventas[posiciones1[1]]);
								//System.out.println(i+"fjlkdasj");
								System.out.println(nombreProductos[pos[i]]);
								System.out.println(precioProductos[pos[i]]);
								System.out.println(vTotales[pos[i]]);
								System.out.println("---");
							}
						}

						break;
					case 5:

						break;
					default:
						System.out.println("Solo numeros entre 1 y 5");
						break;
					}

				} catch (NumberFormatException e) {
					System.out.println("\n  Opcion invalida" + e);

				}
			}
		}

	}

	public static void opciones() {
		System.out.println();
		System.out.println(" ╔══════════════════════════════════════════════════════════════════════════════╗");
		System.out.println(" ║                                   SUPER-25                                   ║");
		System.out.println(" ╠══════════════════════════════════════════════════════════════════════════════╣");
		System.out.println(" ║                                                                              ║");
		System.out.println(" ║                                  MENÚ INCIAL                                 ║");
		System.out.println(" ║                       1. AGREGAR NUEVOS PRODUCTOS                            ║");
		System.out.println(" ║                       2. AGREGAR CUPONES DE DESCUENTO                        ║");
		System.out.println(" ║                       3. REALIZAR VENTAS                                     ║");
		System.out.println(" ║                       4. REALIZAR REPORTE                                    ║");
		System.out.println(" ║                       5. SALIR                                               ║");
		System.out.println(" ║                                                                              ║");
		System.out.println(" ╚══════════════════════════════════════════════════════════════════════════════╝");
		System.out.print("Opcion: ");
	}

	static boolean verificarExistencia(String nPr, String[] nPrs) {
		for (int i = 0; i < nPrs.length; i++) {
			if (nPr.equals(nPrs[i])) {
				return true;
			}
		}
		return false;
	}

	static void imprimirProductos(String[] nombrePrs, double[] precioPrs) {
		System.out.printf("|  No.  |    NOMBRE DEL PRODUCTO    |   PRECIO   |\n");
		for (int i = 0; i < nombrePrs.length; i++) {
			if (nombrePrs[i] != null) {
				System.out.print("| ");
				System.out.printf("%-6s", (i + 1));
				System.out.print("| ");
				System.out.printf("%-26s", nombrePrs[i]);
				System.out.print("| ");
				System.out.printf("Q %-9s", precioPrs[i]);
				System.out.print("|");
				System.out.println();
			}
		}
	}

	static void imprimirFactura(String nCliente, long nitCliente, String[] nProductos, double[] pProdcutos,
			int[] unidades, String codigo, double porce) {
		double total = 0;

		System.out.println("Nombre del cliente: " + nCliente);
		if (nitCliente != 0) {
			System.out.println("Nit del Cliente: " + nitCliente);
		} else {
			System.out.println("Nit del Cliente: C/F");
		}
		System.out.println("Fecha: ");

		for (int i = 0; i < nProductos.length; i++) {
			if (nProductos[i] != null) {
				System.out.print("\n Nombre del Producto" + nProductos[i] + " ");
				System.out.print("Precio del Producto" + pProdcutos[i] + " ");
				System.out.print("Unidades" + unidades[i] + " ");
				System.out.printf("Total/productos: %.2f", pProdcutos[i] * unidades[i]);
				total += pProdcutos[i] * unidades[i];
			}
		}
		System.out.println(" ");
		System.out.printf(" Subtotal: %.2f", total);
		// System.out.println("lo que envia el descuento " + codigo + "-------");
		if (codigo.equals("")) {
			System.out.printf("No ingreso ningun cupón su total es de: %.2f", total);
		} else {
			System.out.println("El descuento es del " + porce);
			total = total - total * (porce / 100);
			System.out.printf("Total: %.2f", total);
		}

	}

	private static int[] burbuja(int[] ventasTotales) {
		int[] posiciones=new int[ventasTotales.length];
		int[] ordenado = ventasTotales.clone();
		
		
		for (int i = 0; i < ordenado.length; i++) {
			for (int j = 0; j < ordenado.length-i-1; j++) {
				if(ordenado[j] < ordenado[j+1]) {
					int temp = ordenado[j];
					ordenado[j] = ordenado[j+1];
					ordenado[j+1]=temp;
					
				}
				
			}
		}
		
		int contador1=0;
		for (int i = 0; i < ordenado.length; i++) {
			for (int j = 0; j < ordenado.length; j++) {
				if(ordenado[i]==ventasTotales[j] && contador1 <= (ordenado.length-1)) {
					System.out.println(ventasTotales[i]+" i: "+i+" j: "+j+ "|"+ordenado[j]);
					posiciones[contador1]=j;
					contador1++;
				}
			}
		}

		return posiciones;

	}

}