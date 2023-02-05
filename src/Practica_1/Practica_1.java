package Practica_1;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Practica_1 {
	// variables para interactuar con el sistema
	static Scanner input = new Scanner(System.in);
	static Scanner inputMenu = new Scanner(System.in);
	static Scanner input_space = new Scanner(System.in).useDelimiter("\n");
	static boolean acceso = false;
	// variables para iniciar sesión
	static String nombre = "";
	static String contraseña = "";
	// tamaño de los arreglos
	static int tamaño = 10;
	// variables para agregar producros
	static int contadorP = 0;
	static String[] nombreProductos = new String[tamaño];
	static double[] precioProductos = new double[tamaño];
	static int[] vTotales = new int[tamaño];
	// variables para agregar descuentos
	static String[] codigoDescuentos = new String[tamaño];
	static double[] porcentajesD = new double[tamaño];
	// contadores
	static int contadorD = 0;
	static int opcion = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inicarSesion();
		if (acceso) {
			while (opcion != 5) {
				menuOpciones();
			}
		}
	}

	public static void menuOpciones() {
		opciones();
		try {
			opcion = inputMenu.nextInt();

		} catch (Exception e) {
			alertas("× ERROR", "Tipo de dato erroneo");
			opcion = 0;
			inputMenu.next();
			return;
		}
		System.out.print("\n");
		switch (opcion) {
		case 1:
			titulos("AGREGAR PRODUCTOS");
			agregarProductos();
			break;
		case 2:
			titulos("AGREGAR CUPONES");
			agregarCupones();
			break;
		case 3:
			titulos("REALIZAR VENTAS");
			realizarVentas();
			break;
		case 4:
			titulos("REALIZAR REPORTE");
			reporte();
			break;
		case 5:
			alertas("» Operación Existosa", "Cierre de Sesión");
			break;
		default:
			alertas("× ERROR", "Ingresar numeros entre 1 y 5");
			break;
		}
	}

	public static void inicarSesion() {
		while (!acceso) {
			titulos("INICIAR SESIÓN");
			System.out.print(" 🙍‍♂️ Ingrese su Usuario: ");
			nombre = input.nextLine();
			System.out.print("\n 🔒 Ingrese su Contraseña: ");
			contraseña = input.nextLine();
			if (nombre.equals("c") && contraseña.equals("i")) {
				acceso = true;
			} else {
				alertas("× ERROR", "Datos incorrectos");
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
		System.out.println(" ║                       1. Agregar Nuevos Productos                            ║");
		System.out.println(" ║                       2. Agregar Cupones de Descuento                        ║");
		System.out.println(" ║                       3. Realizar Ventas                                     ║");
		System.out.println(" ║                       4. Realizar Reporte                                    ║");
		System.out.println(" ║                       5. Salir                                               ║");
		System.out.println(" ║                                                                              ║");
		System.out.println(" ╚══════════════════════════════════════════════════════════════════════════════╝");
		System.out.print("Opcion: ");
	}

	public static void titulos(String titulo) {
		String tImprimir = " ➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖\n" + "  🔸 " + titulo + "\n" + " ➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖\n";
		System.out.println(tImprimir);
	}

	public static void agregarProductos() {

		boolean op1 = true;
		while (op1) {
			String nombreProducto = "";
			double precioProducto = 0;
			System.out.print("Ingrese el Nombre del producto: ");
			nombreProducto = input_space.nextLine();
			precioProducto = ingresarDecimales("Ingrese el Precio del producto: ");

			boolean existencia = verificarExistencia(nombreProducto, nombreProductos);
			if (existencia == true) {
				alertas("× ERROR", "Este producto ya ha sido ingresado anteriormente");
			} else if (!(precioProducto > 0)) {
				alertas("× ERROR", "Debe ingresar un Producto con costo mayor a 0");
			} else if (contadorP > (tamaño - 1)) {
				alertas("× ERROR", "La capacidad máxima de productos ha sido alcanzada");
			} else {
				nombreProductos[contadorP] = nombreProducto;
				precioProductos[contadorP] = Math.round((precioProducto * 100.0)) / 100.0;
				vTotales[contadorP] = 0;
				contadorP++;
				alertas("» Operación Existosa", "Producto Registrado");
			}
			op1 = seguirCiclo("¿Seguir ingresando Productos [si/no]?: ");
			System.out.println();
		}
	}

	public static void agregarCupones() {
		boolean op2 = true;
		while (op2) {
			String codigoD = "";
			double porcentaje = 0.0;
			System.out.print("Ingrese el codigo del descuento: ");
			codigoD = input.next();
			porcentaje = ingresarDecimales("Ingrese el porcentaje del descuento: ");
			boolean existenciaD = verificarExistencia(codigoD, codigoDescuentos);

			if (existenciaD == true) {
				alertas("× ERROR", "Este Cupon ya ha sido ingresado anteriormentea");
			} else if (!(codigoD.length() == 4)) {
				alertas("× ERROR", "Debe ingresar un codigo de 4 caracteres");
			} else if (!(porcentaje > 0 && porcentaje < 100)) {
				alertas("× ERROR", "Solo descuentos mayores a 0 o menores a 100");
			} else if (contadorP > (tamaño - 1)) {
				alertas("× ERROR", "La capacidad máxima de cupones ha sido alcanzada");
			} else {
				codigoDescuentos[contadorD] = codigoD;
				porcentajesD[contadorD] = Math.round((porcentaje * 100.0)) / 100.0;
				contadorD++;
				alertas("» Operación Existosa", "Cupones Registrados");
			}
			op2 = seguirCiclo("¿Seguir ingresando Cupones [si/no]?: ");
			System.out.println();
		}
	}

	public static void realizarVentas() {
		if (contadorP == 0) {
			alertas("■ ADVERTENCIA", "No hay productos ingresados");
			return;
		}
		boolean op3 = true;
		while (op3) {
			String nombreCliente = "";
			long Nit = 0;
			int contadorVentas = 0;
			System.out.print("Ingrese el nombre del Cliente: ");
			nombreCliente = input_space.nextLine();
			System.out.print("");
			Nit = ingresarEnteros("Ingrese el Nit del usuario (Si no posee ingrese 0 ): ");
			int[] cantidadRVentas = new int[tamaño];
			double totalPreliminar = 0;

			if (!nombreCliente.equals("")) {
				imprimirProductos();
				boolean pVentas = true;
				while (pVentas) {
					int auxiliar = ingresarEnteros("Ingrese el No del Producto (Ingrese 0 para culminar la compra): ");
					if (auxiliar == 0) {
						if (contadorVentas == 0) {
							alertas("■ ADVERTENCIA", "No se agrego ningún producto");
						} else {
							break;
						}
					} else if (auxiliar < 0 || auxiliar > contadorP) {
						alertas("■ ADVERTENCIA", "Ingrese un No. Correcto");
					} else {
						int cant = 0;
						cant = ingresarEnteros(
								"Ingrese la cantidad del producto (" + nombreProductos[auxiliar - 1] + "): ");

						if (cant > 0) {
							cantidadRVentas[auxiliar - 1] += cant;
							vTotales[auxiliar - 1] = vTotales[auxiliar - 1] + cant;
							contadorVentas++;
							totalPreliminar += precioProductos[auxiliar - 1] * cant;
							System.out.println("");
						} else {
							alertas("× ERROR", "Solo cantidades mayores a 0");
						}
					}
				}
				System.out.print("\n EL TOTAL PRELIMINAR ES DE: ");
				System.out.printf("%.2f", totalPreliminar);
				System.out.println("");
				boolean descuentoA = true;
				String codigoIngresado = "";
				double porcentaje = 0;

				while (descuentoA) {
					System.out.print("\nTiene algun codigo de descuento (Si no lo tiene escriba no): ");
					codigoIngresado = "";
					porcentaje = 0;
					codigoIngresado = input.next();
					boolean existenciaDIngresado = verificarExistencia(codigoIngresado, codigoDescuentos);
					if (existenciaDIngresado) {
						int posicion = 0;
						for (int i = 0; i < codigoDescuentos.length; i++) {
							if (codigoDescuentos[i].equals(codigoIngresado)) {
								posicion = i;
								descuentoA = false;
								break;
							}
						}
						porcentaje = porcentajesD[posicion];
					} else if (codigoIngresado.equals("no")) {
						codigoIngresado = "";
						descuentoA = false;
					} else {
						alertas("■ ADVERTENCIA", "El codigo no existe, reviselo por favor");
					}
				}
				imprimirFactura(nombreCliente, Nit, cantidadRVentas, codigoIngresado, porcentaje);
				op3 = seguirCiclo("Seguir Realizando Ventas [si/no]: ");
				System.out.println();
			}
		}

	}

	public static void alertas(String tipo, String mensaje) {
		System.out.println();
		System.out.println(" ╔═══════════════════════════════════════════════════╗");
		System.out.print(" ║");
		System.out.printf(" %-50s", tipo);
		System.out.print("║");
		System.out.println("");
		System.out.println(" ╠═══════════════════════════════════════════════════╣");
		System.out.print(" ║");
		System.out.printf(" %-50s", mensaje);
		System.out.print("║");
		System.out.println("");
		System.out.println(" ╚═══════════════════════════════════════════════════╝");
		System.out.println();
	}

	public static boolean verificarExistencia(String vVerficar, String[] vGuardados) {
		for (int i = 0; i < vGuardados.length; i++) {
			if (vGuardados[i] != null && vVerficar.equals(vGuardados[i])) {
				return true;
			}
		}
		return false;
	}

	public static void imprimirProductos() {
		System.out.println("");
		System.out.println("╔═══════╦═══════════════════════════════╦══════════════╗");
		System.out.printf("║  No.  ║      NOMBRE DEL PRODUCTO      ║    PRECIO    ║\n");
		System.out.println("╠═══════╬═══════════════════════════════╬══════════════╣");
		for (int i = 0; i < nombreProductos.length; i++) {
			if (nombreProductos[i] != null) {
				System.out.print("║ ");
				System.out.printf("%-6s", (i + 1));
				System.out.print("║ ");
				System.out.printf("%-30s", nombreProductos[i]);
				System.out.print("║ ");
				System.out.printf("%12.2f", precioProductos[i]);
				System.out.print(" ║");
				System.out.println("");
			}
		}
		System.out.println("╚═══════╩═══════════════════════════════╩══════════════╝");
	}

	public static void imprimirFactura(String nCliente, long nitCliente, int[] unidades, String codigo, double porce) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		double total = 0;
		System.out.println("");
		System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
		System.out.print("║");
		System.out.printf("%80s", "FACTURA ");
		System.out.println("║");
		System.out.println("║                                                                                ║");
		System.out.printf("%-81s", "║ SUPER-25 ");
		System.out.println("║");
		System.out.printf("%-81s", "║ CAJERO: Joab Israel Ajsivinac Ajsivinac ");
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

		System.out.printf("%-81s", "║ Fecha: " + dtf.format(now));
		System.out.println("║");
		System.out.println("║                                                                                ║");
		System.out.println("║ +---------------------------------+-----------------+----------+-------------+ ║");
		System.out.printf("║ |       NOMBRE DEL PRODUCTO       | PRECIO UNITARIO | CANTIDAD |    TOTAL    | ║\n");
		System.out.println("║ +---------------------------------+-----------------+----------+-------------+ ║");
		for (int i = 0; i < nombreProductos.length; i++) {
			if (nombreProductos[i] != null && unidades[i] != 0) {
				System.out.print("║ ");
				System.out.print("| ");
				System.out.printf("%-32s", nombreProductos[i]);
				System.out.print("| ");
				System.out.printf("%15.2f", precioProductos[i]);
				System.out.print(" |");
				System.out.printf("%9s", unidades[i]);
				System.out.print(" | ");
				System.out.printf("%11.2f", precioProductos[i] * unidades[i]);
				System.out.print(" | ");
				System.out.print("║");
				System.out.println("");
				total += precioProductos[i] * unidades[i];
			}
		}
		System.out.println("║ +---------------------------------+-----------------+----------+-------------+ ║");
		System.out.println("║                                                                                ║");
		System.out.print("║");
		System.out.printf("%66s", "SUBTOTAL: ");
		System.out.printf("%12.2f", total);
		System.out.println("  ║");
		System.out.print("║");
		if (codigo.equals("")) {
			System.out.printf("%79s", "No se ingreso ningún cupón de descuento");
		} else {
			String tPorcentaje = String.format("%.2f", porce);
			System.out.printf("%78s ", "El descuento es del " + tPorcentaje + "%");
			total = total - total * (porce / 100);
		}
		System.out.print(" ║");
		System.out.println();
		System.out.println("║                                                                                ║");
		System.out.print("║");
		System.out.printf("%66s", "TOTAL: ");
		System.out.printf("%12.2f", total);
		System.out.println("  ║");
		System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
	}

	public static void reporte() {
		if (contadorP == 0) {
			alertas("■ ADVERTENCIA", "No hay productos ingresados");
			return;
		}

		int[] ventasOrdenadas = new int[vTotales.length];
		ventasOrdenadas = vTotales.clone();
		String[] nombresOrdenados = new String[nombreProductos.length];
		nombresOrdenados = nombreProductos.clone();

		for (int i = 0; i < ventasOrdenadas.length; i++) {
			for (int j = 0; j < ventasOrdenadas.length - i - 1; j++) {
				if (ventasOrdenadas[j] < ventasOrdenadas[j + 1]) {
					int temp = ventasOrdenadas[j];
					ventasOrdenadas[j] = ventasOrdenadas[j + 1];
					ventasOrdenadas[j + 1] = temp;

					String temp1 = nombresOrdenados[j];
					nombresOrdenados[j] = nombresOrdenados[j + 1];
					nombresOrdenados[j + 1] = temp1;
				}
			}
		}
		System.out.println("╔═══════╦════════════════════════════════════╦═════════════════════════════╗");
		System.out.printf("║  No.  ║        NOMBRE DEL PRODUCTO         ║ CANTIDAD DE VECES COMPRADAS ║\n");
		System.out.println("╠═══════╬════════════════════════════════════╬═════════════════════════════╣");
		for (int i = 0; i < ventasOrdenadas.length; i++) {
			if (nombreProductos[i] != null) {
				System.out.print("║ ");
				System.out.printf("%-6s", i + 1);
				System.out.print("║ ");
				System.out.printf("%-35s", nombresOrdenados[i]);
				System.out.print("║ ");
				System.out.printf("%-28s", ventasOrdenadas[i]);
				System.out.println("║");
			}
		}
		System.out.println("╚═══════╩════════════════════════════════════╩═════════════════════════════╝");
	}

	public static boolean seguirCiclo(String mensaje) {
		String decision = "";
		boolean activar = true;
		while (activar) {
			System.out.print(mensaje);
			decision = input_space.nextLine();
			if (decision.equals("no") || decision.equals("No")) {
				activar = false;
			} else if (decision.equals("si") || decision.equals("Si")) {
				activar = true;
				break;
			} else {
				System.out.println("Por favor ingresar si/no");
			}
		}
		return activar;
	}

	public static int ingresarEnteros(String texto) {
		int valor = 0;
		do {
			try {
				System.out.print(texto);
				valor = input.nextInt();
				break;
			} catch (Exception e) {
				alertas("× ERROR", "Solo se admiten números Enteros");
				input.next();
			}
		} while (true);
		return valor;
	}

	public static double ingresarDecimales(String texto) {
		double valor = 0;
		do {
			try {
				System.out.print(texto);
				valor = input.nextDouble();
				break;
			} catch (Exception e) {
				alertas("× ERROR", "Solo se admiten números decimales/Enteros");
				input.next();
			}
		} while (true);
		return valor;
	}
}