package Practica_1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Practica_1 {
	// variables para interactuar con el sistema
	static Scanner input = new Scanner(System.in);
	static Scanner input_space = new Scanner(System.in).useDelimiter("\n");
	static boolean acceso = false;
	// variables para iniciar sesión
	static String nombre = "";
	static String contraseña = "";
	// variables para agregar producros
	static int tamaño = 10;
	static int contadorP = 0;
	static String[] nombreProductos = new String[tamaño];
	static double[] precioProductos = new double[tamaño];
	static int[] vTotales = new int[tamaño];

	// variables para agregar descuentos
	static String[] codigoDescuentos = new String[tamaño];
	static double[] porcentajesD = new double[tamaño];
	static int contadorD = 0;

	static int contadorVentas = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		inicarSesion();
		if (acceso) {
			System.out.println("Bienvenido");
			while (opcion != 5) {
				try {
					opciones();
					opcion = input.nextInt();
					switch (opcion) {
					case 1:
						agregarProductos();
						break;
					case 2:
						agregarCupones();
						break;
					case 3:
						realizarVentas();
						break;
					case 4:
						System.out.println(Arrays.toString(vTotales));
						int[] pos = burbuja(vTotales);
						// System.out.println(vTotales[pos.length-1]);
						for (int i = 0; i < pos.length; i++) {
							if (nombreProductos[i] != null) {
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

				} catch (InputMismatchException e) {
					System.out.println("\n  Opcion invalida" + e);
					break;
				}
			}
		}

	}

	public static void inicarSesion() {
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

	public static void agregarProductos() {
		boolean op1 = true;
		while (op1) {
			String nombreProducto = "";
			double precioProducto = 0;
			System.out.print("Ingrese el Nombre del producto: ");
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
	}

	public static void agregarCupones() {
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
	}

	public static void realizarVentas() {
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
					System.out.println("No se ha agregado ningún producto");
					break;
				} else if (auxiliar < 0 || auxiliar > contadorP) {
					System.out.println("Ingrese un No. correcto");
				} else {
					System.out.print("Ingrese la cantidad de" + nombreProductos[auxiliar - 1] + ": ");
					int cant = input.nextInt();
					cantidadRVentas[auxiliar - 1] += cant;
					vTotales[auxiliar - 1] = vTotales[auxiliar - 1] + cant;
					noProducto[contadorVentas] = auxiliar;
					contadorVentas++;
				}

			}
			System.out.print("Tiene algun codigo de descuento: ");
			String codigoIngresado = input.next();
			boolean existenciaDIngresado = verificarExistencia(codigoIngresado, codigoDescuentos);
			double porcentaje = 0;
			System.out.println(existenciaDIngresado);
			if (existenciaDIngresado) {
				int posicion = 0;
				for (int i = 0; i < codigoDescuentos.length; i++) {
					if (codigoDescuentos[i].equals(codigoIngresado)) {
						//System.out.println(i+"  " +codigoDescuentos[i] + "    " + codigoIngresado);
						posicion = i;
						break;
					}
				}
				// total = total - total * (porcentajesD[posicion] / 100);
				porcentaje = porcentajesD[posicion];
			} else {
				codigoIngresado = "";
			}
			//System.out.println(Arrays.toString(porcentajesD)+"  "+porcentaje);
			imprimirFactura(nombreCliente, Nit, cantidadRVentas, codigoIngresado, porcentaje);

		}
	}

	public static void alertas(String tipo, String mensaje) {
		System.out.println();
		System.out.println(" ╔═════════════════════════════════════════╗");
		System.out.print(" ║");
		System.out.printf(" %-40s", tipo);
		System.out.print("║");
		System.out.println("");
		System.out.println(" ╠═════════════════════════════════════════╣");
		System.out.print(" ║");
		System.out.printf(" %-40s", mensaje);
		System.out.print("║");
		System.out.println("");
		System.out.println(" ╚═════════════════════════════════════════╝");
	}

	static boolean verificarExistencia(String vVerficar, String[] vGuardados) {
		for (int i = 0; i < vGuardados.length; i++) {
			if (vVerficar.equals(vGuardados[i])) {
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

	static void imprimirFactura(String nCliente, long nitCliente, int[] unidades, String codigo, double porce) {
		double total = 0;
		System.out.println("");

		System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
		System.out.print("║");
		System.out.printf("%80s", "FACTURA ");
		System.out.println("║");
		System.out.println("║                                                                                ║");
		System.out.printf("%-81s", "║ SUPER-25 ");
		System.out.println("║");
		System.out.println("║                                                                                ║");
		System.out.printf("%-81s", "║ Nombre del Cliente: " + nCliente);
		System.out.println("║");
		if (nitCliente != 0) {
			System.out.printf("%-81s", "║ Nit del Cliente: " + nitCliente);
			System.out.println("║");
		} else {
			System.out.printf("%-81s", "║ Nit del Cliente: C/F");
			System.out.println("║");
		}

		System.out.printf("%-81s", "║ Fecha: 31/01/2023");
		System.out.println("║");
		System.out.println("║                                                                                ║");
		System.out.printf("║ |       NOMBRE DEL PRODUCTO       | PRECIO UNITARIO | CANTIDAD |    TOTAL    | ║\n");
		String espacio = "";
		for (int i = 0; i < nombreProductos.length; i++) {
			if (nombreProductos[i] != null && unidades[i] != 0) {
				System.out.print("║ ");
				System.out.print("| ");
				System.out.printf("%-32s", nombreProductos[i]);
				System.out.print("| ");
				System.out.printf("%16.2f", precioProductos[i]);
				System.out.print("|");
				System.out.printf("%10s", unidades[i]);
				System.out.print("| ");
				System.out.printf("%12.2f", precioProductos[i] * unidades[i]);
				System.out.print("| ");
				System.out.print("║");
				System.out.println("");
				total += precioProductos[i] * unidades[i];
				
			}
		}
		System.out.println("║                                                                                ║");
		System.out.print("║");
		System.out.printf("%66s", "SUBTOTAL: ");
		System.out.printf("%12.2f", total);
		System.out.println("  ║");
		System.out.print("║");
		if (codigo.equals("")) {
			System.out.printf("%78s","No se ingreso ningún cupón de descuento");
		} else {
			System.out.printf("%78s","El descuento es del " + porce +"%");
			total = total - total * (porce / 100);
		}
		System.out.print("  ║");
		System.out.println();
		System.out.print("║");
		System.out.printf("%66s", "TOTAL: ");
		System.out.printf("%12.2f", total);
		System.out.println("  ║");
		System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
	}

	private static int[] burbuja(int[] ventasTotales) {
		int[] posiciones = new int[ventasTotales.length];
		int[] ordenado = ventasTotales.clone();

		for (int i = 0; i < ordenado.length; i++) {
			for (int j = 0; j < ordenado.length - i - 1; j++) {
				if (ordenado[j] < ordenado[j + 1]) {
					int temp = ordenado[j];
					ordenado[j] = ordenado[j + 1];
					ordenado[j + 1] = temp;
				}
			}
		}

		int contador1 = 0;
		for (int i = 0; i < ordenado.length; i++) {
			for (int j = 0; j < ordenado.length; j++) {
				if (ordenado[i] == ventasTotales[j] && contador1 <= (ordenado.length - 1)) {
					posiciones[contador1] = j;
					contador1++;
				}
			}
		}
		return posiciones;
	}

}